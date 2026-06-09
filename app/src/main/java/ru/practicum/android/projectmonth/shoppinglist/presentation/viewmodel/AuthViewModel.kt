package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.domain.models.LoginCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.RegisterCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.SecurityState
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.UiSecurityState

open class AuthViewModel(
    val interactor : AuthInteractor
) : ViewModel() {

    private val _authState = MutableStateFlow<UiSecurityState>(UiSecurityState.Default)
    val authState: StateFlow<UiSecurityState> = _authState.asStateFlow()
    private val _registerState = MutableStateFlow<UiSecurityState>(UiSecurityState.Default)
    val registerState: StateFlow<UiSecurityState> = _registerState.asStateFlow()
    private val _checkAuthState = MutableStateFlow<UiSecurityState>(UiSecurityState.Default)
    val checkAuthState: StateFlow<UiSecurityState> = _checkAuthState.asStateFlow()

    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = UiSecurityState.Loading
            interactor.register(RegisterCredentials(email, password))
                .collect {
                    resolveSecurityState(it, _registerState)
                }
        }
    }

    fun login(email: String, password: String) {
        _authState.value = UiSecurityState.Loading
        viewModelScope.launch {
            interactor.authenticate(LoginCredentials(email, password))
                .collect {
                    resolveSecurityState(it, _authState)
                }
        }
    }

    private fun resolveSecurityState(
        it: SecurityState,
        state: MutableStateFlow<UiSecurityState>
    ) {
        when(it) {
            SecurityState.Default -> {}
            SecurityState.Loading -> {}
            is SecurityState.SuccessAuth -> {
                state.value = UiSecurityState.Success
//                navController.navigate(Destination.ShoppingLists.route)
            }
            is SecurityState.SuccessRegister -> {
                state.value = UiSecurityState.Success
            }
            is SecurityState.CheckAuthSuccess -> {
                state.value = UiSecurityState.Success
            }
            is SecurityState.ErrorAuth -> {
                state.value = UiSecurityState.Error("code=${it.errCode} ${it.message}")
//                authError = (securityState as SecurityState.ErrorAuth).message?: ""
            }
            is SecurityState.ErrorRegister -> {
                state.value = UiSecurityState.Error("code=${it.errCode} ${it.message}")
//                registerError = (securityState as SecurityState.ErrorRegister).message?: ""
            }
            is SecurityState.CheckAuthError -> {
                state.value = UiSecurityState.Error("code=${it.errCode} ${it.message}")
            }
        }

    }

    fun refreshRegisterState() {
        _registerState.value = UiSecurityState.Default

    }


}
package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.LoginCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.RegisterCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.SecurityState

class MockAuthInteractor : AuthInteractor {
    override fun authenticate(loginCredentials: LoginCredentials): Flow<SecurityState> = flow {
        emit(SecurityState.Default)
    }

    override fun register(registerCredentials: RegisterCredentials): Flow<SecurityState> = flow {
        emit(SecurityState.Default)
    }

}
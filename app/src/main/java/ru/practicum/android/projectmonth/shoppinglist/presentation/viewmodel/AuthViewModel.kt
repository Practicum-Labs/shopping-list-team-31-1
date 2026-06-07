package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor

open class AuthViewModel(
    val interactor : AuthInteractor
) : ViewModel() {


    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun register(email: String, password: String, onRegistrationSuccess: (success: Boolean, errorMessage: String) -> Unit) {
        onRegistrationSuccess(true, "")
    }


}
package ru.practicum.android.projectmonth.shoppinglist.presentation.state

sealed interface UiSecurityState {
    data object Default : UiSecurityState
    data object Loading : UiSecurityState
    data object Success : UiSecurityState
    data class Error(val errMsg: String) : UiSecurityState
}
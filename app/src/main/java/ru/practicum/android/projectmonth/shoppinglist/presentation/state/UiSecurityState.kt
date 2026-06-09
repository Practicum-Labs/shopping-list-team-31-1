package ru.practicum.android.projectmonth.shoppinglist.presentation.state

sealed interface UiSecurityState {
    data object Default : UiSecurityState
    data object Loading : UiSecurityState
    data class Success(val msg: String = "") : UiSecurityState
    data class Error(val errMsg: String) : UiSecurityState
}

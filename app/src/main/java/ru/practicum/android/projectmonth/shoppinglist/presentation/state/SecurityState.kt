package ru.practicum.android.projectmonth.shoppinglist.presentation.state

sealed interface SecurityState {

    data object Default: SecurityState
    data object Loading : SecurityState
    data class SuccessAuth(val accessToken: String?): SecurityState
    data class ErrorAuth(val message: String?, val errCode: Int): SecurityState
    data class SuccessRegister(val message: String): SecurityState
    data class ErrorRegister(val message: String?, val errCode: Int): SecurityState
    class CheckAuthSuccess : SecurityState
    data class CheckAuthError(val message: String?, val errCode: Int): SecurityState

}
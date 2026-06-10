package ru.practicum.android.projectmonth.shoppinglist.data.network.dto

sealed interface SecurityState {

    abstract class ErrorState(open val message: String?, open val errCode: Int): SecurityState
    data object Default: SecurityState
    data object Loading : SecurityState
    data class SuccessAuth(val accessToken: String?): SecurityState
    data class SuccessRegister(val message: String): SecurityState
    data class SuccessRecoveryPasswd(val message: String): SecurityState
    class CheckAuthSuccess : SecurityState
    data class ErrorAuth(override val message: String?, override val errCode: Int)
        : ErrorState(message, errCode)
    data class ErrorRegister(override val message: String?, override val errCode: Int)
        : ErrorState(message, errCode)
    data class ErrorRecoveryPasswd(override val message: String?, override val errCode: Int)
        : ErrorState(message, errCode)
    data class CheckAuthError(override val message: String?, override val errCode: Int)
        : ErrorState(message, errCode)

}
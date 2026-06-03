package ru.practicum.android.projectmonth.shoppinglist.data

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.projectmonth.shoppinglist.data.converter.network.AuthConverter
import ru.practicum.android.projectmonth.shoppinglist.data.network.AuthApiService
import ru.practicum.android.projectmonth.shoppinglist.domain.AuthRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.LoginCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.RegisterCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.SecurityState

class AuthRepositoryImpl(
    val sharedPreferences: SharedPreferences,
    val authApiService: AuthApiService,
    val authConverter: AuthConverter
): AuthRepository {

    override fun registerUser(registerCredentials: RegisterCredentials): Flow<SecurityState> = flow {
        val response = authApiService.registerUser(authConverter.map(registerCredentials))
        val responseCode = response.code()

        if(responseCode == SUCCESS_CODE) {
            val respBody = response.body()
            sharedPreferences.edit {
                putString(ACCESS_TOKEN, respBody?.accessToken)
                putString(REFRESH_TOKEN, respBody?.refreshToken)
            }
            emit(SecurityState.SuccessRegister("success register. User ID = ${respBody?.userid}"))
        } else {
            emit(SecurityState.ErrorRegister(message = response.errorBody()?.string(), errCode = response.code()))
        }

    }
        .flowOn(Dispatchers.IO)

    override fun authenticate(loginCredentials: LoginCredentials): Flow<SecurityState> = flow {
        val response = authApiService.loginUser(authConverter.map(loginCredentials))
        val responseCode = response.code()

        if(responseCode == SUCCESS_CODE) {
            val respBody = response.body()
            sharedPreferences.edit {
                putString(ACCESS_TOKEN, respBody?.accessToken)
                putString(REFRESH_TOKEN, respBody?.refreshToken)
            }
            emit(SecurityState.SuccessAuth(sharedPreferences.getString(ACCESS_TOKEN, null)))
        } else {
            emit(SecurityState.ErrorAuth(message = response.errorBody()?.string(), errCode = response.code()))
        }

    }
        .flowOn(Dispatchers.IO)

    override fun refreshAccessToken(): Flow<SecurityState> = flow {
        val refreshToken = sharedPreferences.getString(REFRESH_TOKEN, null)
        if (refreshToken == null) {
            emit(SecurityState.ErrorAuth(NO_REFRESH_TOKEN, INTERNAL_ERROR_CODE))
            return@flow
        }

        val response = authApiService.refreshAccessToken(refreshToken)
        val responseCode = response.code()

        if(responseCode == SUCCESS_CODE) {
            val respBody = response.body()
            sharedPreferences.edit {
                putString(ACCESS_TOKEN, respBody?.accessToken)
                putString(REFRESH_TOKEN, respBody?.refreshToken)
            }
            emit(SecurityState.SuccessAuth(sharedPreferences.getString(ACCESS_TOKEN, null)))
        } else {
            emit(SecurityState.ErrorAuth(message = response.errorBody()?.string(), errCode = response.code()))
        }
    }
        .flowOn(Dispatchers.IO)


    override fun checkAuthorization(): Flow<SecurityState> = flow {
        val accessToken = sharedPreferences.getString(ACCESS_TOKEN, null)
        if (accessToken == null) {
            emit(SecurityState.CheckAuthError(NO_ACCESS_TOKEN, INTERNAL_ERROR_CODE))
            return@flow
        }
        val response = authApiService.checkAuthorization(accessToken)
        val responseCode = response.code()

        if(responseCode == SUCCESS_CODE) {
            emit(SecurityState.CheckAuthSuccess())
        } else {
            emit(SecurityState.CheckAuthError(message = response.errorBody()?.string(), errCode = response.code()))
        }

    }
        .flowOn(Dispatchers.IO)


    companion object {
        const val INTERNAL_ERROR_CODE = -1
        const val NO_ACCESS_TOKEN = "В приложении не был сохранен access_token"
        const val NO_REFRESH_TOKEN = "В приложении не был сохранен refresh_token"
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val SUCCESS_CODE = 200
    }
}

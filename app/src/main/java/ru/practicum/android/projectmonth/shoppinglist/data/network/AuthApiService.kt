package ru.practicum.android.projectmonth.shoppinglist.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.CheckAuthorizationResponse
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.LoginRequest
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.LoginResponse
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.RefreshResponse
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.RegistrationRequest
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.RegistrationResponse

interface AuthApiService {

    @POST("auth/registration")
    suspend fun registerUser(
        @Body request: RegistrationRequest
    ): Response<RegistrationResponse>

    @POST("auth/login")
    suspend fun loginUser(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("auth/refresh")
    @FormUrlEncoded
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String,
    ): Response<RefreshResponse>

    @POST("auth/recovery")
    @FormUrlEncoded
    suspend fun recoveryPassword (
        @Field("email") email: String,
    ): Response<Unit>

    @GET("auth/check")
    suspend fun checkAuthorization(
        @Header("Authorization") accessBearerToken: String
    ): Response<CheckAuthorizationResponse>

}

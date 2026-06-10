package ru.practicum.android.projectmonth.shoppinglist.data.network.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user_id")
    val userid: Long,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)

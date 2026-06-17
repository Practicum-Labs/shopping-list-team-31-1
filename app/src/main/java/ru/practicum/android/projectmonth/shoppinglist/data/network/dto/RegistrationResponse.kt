package ru.practicum.android.projectmonth.shoppinglist.data.network.dto

data class RegistrationResponse(
    val userid: Long,
    val accessToken: String,
    val refreshToken: String
)

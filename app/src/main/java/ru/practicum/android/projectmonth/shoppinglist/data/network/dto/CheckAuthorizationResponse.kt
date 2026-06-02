package ru.practicum.android.projectmonth.shoppinglist.data.network.dto

import com.google.gson.annotations.SerializedName

data class CheckAuthorizationResponse (
    @SerializedName("is_valid")
    val isValid: Boolean,
    val success: Boolean
)

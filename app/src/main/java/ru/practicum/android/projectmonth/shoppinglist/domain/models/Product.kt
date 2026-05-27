package ru.practicum.android.projectmonth.shoppinglist.domain.models

data class Product(
    val id: Int,
    val name: String,
    val checked: Boolean,
    val number: Float,
    val measureUnit: String
)
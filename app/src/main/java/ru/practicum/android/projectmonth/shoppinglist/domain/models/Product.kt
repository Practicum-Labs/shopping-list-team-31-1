package ru.practicum.android.projectmonth.shoppinglist.domain.models

data class Product(
    val id: Long,
    val name: String,
    val checked: Boolean,
    val number: Float,
    val measureUnit: String,
    val shoppingListId: Long
)
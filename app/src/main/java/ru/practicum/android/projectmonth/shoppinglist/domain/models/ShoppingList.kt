package ru.practicum.android.projectmonth.shoppinglist.domain.models

data class ShoppingList(
    val id: Long,
    val name: String,
    val iconRes: String,
    val products: List<Product>,
    val sortType: Int,
    val login: String = ""
)

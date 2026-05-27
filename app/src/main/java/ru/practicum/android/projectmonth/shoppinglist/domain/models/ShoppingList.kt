package ru.practicum.android.projectmonth.shoppinglist.domain.models

data class ShoppingList(
    val id: Int,
    val name: String,
    val iconResId: Int,
    val products: MutableList<Product>
)
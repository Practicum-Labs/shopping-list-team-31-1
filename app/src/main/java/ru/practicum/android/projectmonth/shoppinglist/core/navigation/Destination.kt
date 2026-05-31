package ru.practicum.android.projectmonth.shoppinglist.core.navigation

sealed class Destination(val route: String) {
    object Main : Destination("main")
    object ShoppingLists : Destination("shopping_lists")
    object Products : Destination("products/{shoppingListId}") {
        fun createRoute(shoppingListId: Long): String = "products/$shoppingListId"
    }
}

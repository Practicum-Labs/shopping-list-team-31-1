package ru.practicum.android.projectmonth.shoppinglist.core.navigation

sealed class Destination(val route: String) {
    object Main : Destination("main")
}
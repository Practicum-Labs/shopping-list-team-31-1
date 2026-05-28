package ru.practicum.android.projectmonth.shoppinglist.presentation.state

import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

sealed interface ShoppingListsState {
    data object Empty : ShoppingListsState
    data class Content(val data: List<ShoppingList>) : ShoppingListsState
}
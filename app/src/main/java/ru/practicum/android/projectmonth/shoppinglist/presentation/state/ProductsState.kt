package ru.practicum.android.projectmonth.shoppinglist.presentation.state

import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product

sealed interface ProductsState {
    data object Empty : ProductsState
    data class Content(val data: List<Product>) : ProductsState
}

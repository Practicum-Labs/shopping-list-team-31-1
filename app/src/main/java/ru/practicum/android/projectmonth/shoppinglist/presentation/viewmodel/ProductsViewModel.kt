package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.ProductsState

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
    private val productInteractor: ProductInteractor
) : ViewModel() {

    val shoppingListId: Long = checkNotNull(savedStateHandle["shoppingListId"])

    var uiState by mutableStateOf<ProductsState>(ProductsState.Empty)
        private set

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productInteractor.getProductsByShoppingListId(shoppingListId).collect { result ->
                uiState = if (result.isNotEmpty()) {
                    ProductsState.Content(data = result)
                } else {
                    ProductsState.Empty
                }
            }
        }
    }

    fun addProduct(name: String, number: Float, measureUnit: String) {
        viewModelScope.launch {
            productInteractor.saveNewProduct(
                Product(
                    id = 0L,
                    name = name,
                    checked = false,
                    number = number,
                    measureUnit = measureUnit,
                    shoppingListId = shoppingListId
                )
            ).collect {
                getProducts()
            }
        }
    }

    fun checkProduct(product: Product, isChecked: Boolean) {
        viewModelScope.launch {
            productInteractor.updateProduct(
                id = product.id,
                product = product.copy(checked = isChecked)
            ).collect {
                getProducts()
            }
        }
    }
}

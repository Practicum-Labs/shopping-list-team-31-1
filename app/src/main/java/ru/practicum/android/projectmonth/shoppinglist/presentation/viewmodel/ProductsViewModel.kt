package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.domain.models.SortType
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.ProductsState
import kotlin.collections.sortedBy

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
    private val productInteractor: ProductInteractor,
    private val shoppingListInteractor: ShoppingListInteractor
) : ViewModel() {

    val shoppingListId: Long = checkNotNull(savedStateHandle["shoppingListId"])

    var uiState by mutableStateOf<ProductsState>(ProductsState.Empty)
        private set

    var currentSortType by mutableStateOf(SortType.NONE)
        private set

    private var shoppingList: ShoppingList? = null

    private var productsJob: Job? = null

    init {
        getProducts()
    }

    fun getProducts() {
        productsJob?.cancel()

        productsJob = viewModelScope.launch {
            val shoppingListFlow = shoppingListInteractor.getShoppingListById(shoppingListId)
            val productsFlow = productInteractor.getProductsByShoppingListId(shoppingListId)

            combine(shoppingListFlow, productsFlow) { list, products ->
                shoppingList = list
                currentSortType = SortType.entries.find { it.index == list?.sortType } ?: SortType.NONE

                applySort(products)
            }.collect { sortedProducts ->
                uiState = if (sortedProducts.isNotEmpty()) {
                    ProductsState.Content(data = sortedProducts)
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
            ).collect { }
        }
    }

    fun checkProduct(product: Product, isChecked: Boolean) {
        viewModelScope.launch {
            productInteractor.updateProduct(
                id = product.id,
                product = product.copy(checked = isChecked)
            ).collect { }
        }
    }

    fun updateProduct(productId: Long, name: String, number: Float, measureUnit: String) {
        viewModelScope.launch {
            productInteractor.updateProduct(
                id = productId,
                product = Product(
                    id = productId,
                    name = name,
                    checked = false,
                    number = number,
                    measureUnit = measureUnit,
                    shoppingListId = shoppingListId
                )
            ).collect { }
        }
    }

    fun removeProduct(productId: Long) {
        viewModelScope.launch {
            productInteractor.removeProduct(productId)
        }
    }

    fun clearPurchasedProduct() {
        val currentState = uiState

        if (currentState is ProductsState.Content) {
            val itemsToDelete = currentState.data.filter{ it.checked }

            if (itemsToDelete.isNotEmpty()) {
                viewModelScope.launch {
                    itemsToDelete.forEach { product ->
                        productInteractor.removeProduct(product.id)
                    }
                }
            }
        }
    }

    fun deleteAllProducts() {
        viewModelScope.launch {
            productInteractor.deleteShoppingListProducts(shoppingListId)
        }
    }

    fun setSortType(sortType: SortType) {
        currentSortType = sortType

        val currentState = uiState

        if (currentState is ProductsState.Content) {
            uiState = currentState.copy(data = applySort(currentState.data))
        }

        updateShoppingListSortType()
    }

    private fun updateShoppingListSortType() {
        shoppingList?.let { list ->
            viewModelScope.launch {
                shoppingListInteractor.updateShoppingList(
                    id = shoppingListId,
                    shoppingList = list.copy(sortType = currentSortType.index)
                ).collect {  }
            }
        }
    }

    private fun applySort(products: List<Product>): List<Product> {
        return when (currentSortType) {
            SortType.ALPHABETICAL -> products.sortedBy { it.name.lowercase() }
            else -> products
        }
    }
}

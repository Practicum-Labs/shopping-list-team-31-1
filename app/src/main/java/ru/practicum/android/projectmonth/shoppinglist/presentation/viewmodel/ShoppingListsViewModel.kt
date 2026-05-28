package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.ShoppingListsState

private const val DEFAULT_SHOPPING_LIST_ICON = "ic_shopping_list_default"

class ShoppingListsViewModel(
    private val shoppingListInteractor: ShoppingListInteractor
) : ViewModel() {

    var uiState by mutableStateOf<ShoppingListsState>(ShoppingListsState.Empty)
        private set

    init {
        getShoppingLists()
    }

    fun getShoppingLists() {
        viewModelScope.launch {
            shoppingListInteractor.getAllShoppingLists().collect { result ->
                if (result.isNotEmpty()) {
                    uiState = ShoppingListsState.Content(result)
                }
            }
        }
    }

    fun newShoppingList(name: String) {
        viewModelScope.launch {
            shoppingListInteractor.saveNewShoppingList(
                ShoppingList(
                    id = 0L,
                    name = name,
                    iconRes = DEFAULT_SHOPPING_LIST_ICON,
                    products = emptyList()
                )
            ).collect {
                getShoppingLists()
            }
        }
    }
}
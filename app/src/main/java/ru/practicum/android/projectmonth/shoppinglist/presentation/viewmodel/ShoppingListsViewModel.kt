package ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.ShoppingListsState

private const val DEFAULT_SHOPPING_LIST_ICON = "ic_shopping_list_default"

class ShoppingListsViewModel(
    private val shoppingListInteractor: ShoppingListInteractor,
    private val authInteractor: AuthInteractor
) : ViewModel() {

    var uiState by mutableStateOf<ShoppingListsState>(ShoppingListsState.Empty)
        private set

    init {
        getShoppingLists()
    }

    fun getShoppingLists() {
        uiState = ShoppingListsState.Empty
        viewModelScope.launch {
            val result = shoppingListInteractor.getAllShoppingLists()
            if (result.isNotEmpty()) {
                uiState = ShoppingListsState.Content(result)
            } else {
                uiState = ShoppingListsState.Empty
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
                    products = emptyList(),
                    sortType = 0,
                    login = authInteractor.currentUser()
                )
            ).collect {
                getShoppingLists()
            }
        }
    }

    fun deleteShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            shoppingListInteractor.deleteShoppingList(shoppingList)
            getShoppingLists()
        }
    }

    fun deleteAllShoppingLists() {
        viewModelScope.launch {
            shoppingListInteractor.deleteAllShoppingLists()
            getShoppingLists()
        }
    }

    fun updateShoppingList(shoppingList: ShoppingList, newName: String) {
        if (newName.isBlank() || newName == shoppingList.name) return
        viewModelScope.launch {
            val updatedList = shoppingList.copy(name = newName)
            shoppingListInteractor.updateShoppingList(updatedList.id, updatedList).collect {
                getShoppingLists()
            }
        }
    }

    fun copyShoppingList(shoppingList: ShoppingList) {

    }
}

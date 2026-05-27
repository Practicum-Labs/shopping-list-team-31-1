package ru.practicum.android.projectmonth.shoppinglist.domain.usecaces

import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

interface ShoppingListInteractor {

    suspend fun getAllShoppingLists(): List<ShoppingList>
    suspend fun getShoppingListById(id: Long): ShoppingList
    suspend fun updateShoppingList(id: Long, shoppingList: ShoppingList): ShoppingList
    suspend fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Long
    suspend fun saveNewShoppingList(shoppingList: ShoppingList): ShoppingList

}
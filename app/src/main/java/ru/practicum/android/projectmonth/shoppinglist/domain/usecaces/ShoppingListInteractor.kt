package ru.practicum.android.projectmonth.shoppinglist.domain.usecaces

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

interface ShoppingListInteractor {

    suspend fun getAllShoppingLists(): List<ShoppingList>
    fun getShoppingListById(id: Long): Flow<ShoppingList?>
    fun updateShoppingList(id: Long, shoppingList: ShoppingList): Flow<ShoppingList>
    fun saveNewShoppingList(shoppingList: ShoppingList): Flow<ShoppingList>
    suspend fun deleteShoppingList(shoppingList: ShoppingList)
    suspend fun deleteAllShoppingLists()
}

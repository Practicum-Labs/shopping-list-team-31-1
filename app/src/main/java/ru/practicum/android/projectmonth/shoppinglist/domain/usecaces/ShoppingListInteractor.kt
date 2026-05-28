package ru.practicum.android.projectmonth.shoppinglist.domain.usecaces

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

interface ShoppingListInteractor {

    fun getAllShoppingLists(): Flow<List<ShoppingList>>
    fun getShoppingListById(id: Long): Flow<ShoppingList?>
    fun updateShoppingList(id: Long, shoppingList: ShoppingList): Flow<ShoppingList>
    fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Flow<Long>
    fun saveNewShoppingList(shoppingList: ShoppingList): Flow<ShoppingList>

}

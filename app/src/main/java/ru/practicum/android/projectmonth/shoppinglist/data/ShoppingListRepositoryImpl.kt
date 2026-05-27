package ru.practicum.android.projectmonth.shoppinglist.data

import ru.practicum.android.projectmonth.shoppinglist.domain.ShoppingListRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

class ShoppingListRepositoryImpl: ShoppingListRepository {
    override suspend fun getAllShoppingLists(): List<ShoppingList> {
        TODO("Not yet implemented")
    }

    override suspend fun getShoppingListById(id: Long): ShoppingList {
        TODO("Not yet implemented")
    }

    override suspend fun updateShoppingList(
        id: Long,
        shoppingList: ShoppingList
    ): ShoppingList {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Long {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewShoppingList(shoppingList: ShoppingList): ShoppingList {
        TODO("Not yet implemented")
    }
}
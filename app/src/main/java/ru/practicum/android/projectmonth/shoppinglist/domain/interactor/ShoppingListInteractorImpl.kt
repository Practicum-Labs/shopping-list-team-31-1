package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import ru.practicum.android.projectmonth.shoppinglist.domain.ShoppingListRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor

class ShoppingListInteractorImpl(val repository: ShoppingListRepository): ShoppingListInteractor {
    override suspend fun getAllShoppingLists(): List<ShoppingList> {
        return repository.getAllShoppingLists()
    }

    override suspend fun getShoppingListById(id: Long): ShoppingList {
        return repository.getShoppingListById(id)
    }

    override suspend fun updateShoppingList(
        id: Long,
        shoppingList: ShoppingList
    ): ShoppingList {
        return repository.updateShoppingList(id, shoppingList)
    }

    override suspend fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Long {
        return repository.saveNewShoppingListAndReturnId(shoppingList)
    }

    override suspend fun saveNewShoppingList(shoppingList: ShoppingList): ShoppingList {
        return repository.saveNewShoppingList(shoppingList)
    }
}
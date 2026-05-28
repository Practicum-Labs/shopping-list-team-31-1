package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.ShoppingListRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor

class ShoppingListInteractorImpl(val repository: ShoppingListRepository): ShoppingListInteractor {
    override fun getAllShoppingLists(): Flow<List<ShoppingList>> {
        return repository.getAllShoppingLists()
    }

    override fun getShoppingListById(id: Long): Flow<ShoppingList?> {
        return repository.getShoppingListById(id)
    }

    override fun updateShoppingList(
        id: Long,
        shoppingList: ShoppingList
    ): Flow<ShoppingList> {
        return repository.updateShoppingList(id, shoppingList)
    }

    override fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Flow<Long> {
        return repository.saveNewShoppingListAndReturnId(shoppingList)
    }

    override fun saveNewShoppingList(shoppingList: ShoppingList): Flow<ShoppingList> {
        return repository.saveNewShoppingList(shoppingList)
    }
}
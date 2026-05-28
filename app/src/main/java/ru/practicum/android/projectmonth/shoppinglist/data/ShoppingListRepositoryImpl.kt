package ru.practicum.android.projectmonth.shoppinglist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ShoppingListDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.db.AppDatabase
import ru.practicum.android.projectmonth.shoppinglist.domain.ShoppingListRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

class ShoppingListRepositoryImpl(
    val appDatabase: AppDatabase,
    val shoppingListDbConverter: ShoppingListDbConverter
): ShoppingListRepository {

    override fun getAllShoppingLists(): Flow<List<ShoppingList>> =
        appDatabase.shoppingListDao().getAll()
            .map { entities -> entities.mapNotNull { shoppingListDbConverter.map(it) } }


    override fun getShoppingListById(id: Long): Flow<ShoppingList?> = flow {
        emitAll( appDatabase.shoppingListDao().getById(id).map { shoppingListDbConverter.map(it) })
    }

    override fun updateShoppingList(
        id: Long,
        shoppingList: ShoppingList
    ): Flow<ShoppingList> = flow {
        appDatabase.shoppingListDao().update(shoppingListDbConverter.map(shoppingList))
        emit(appDatabase.shoppingListDao().getById(shoppingList.id)
            .mapNotNull { shoppingListDbConverter.map(it) }
            .first())
    }


    override fun saveNewShoppingListAndReturnId(shoppingList: ShoppingList): Flow<Long> = flow {
        emit(appDatabase.shoppingListDao().insert(shoppingListDbConverter.map(shoppingList)))
    }

    override fun saveNewShoppingList(shoppingList: ShoppingList): Flow<ShoppingList> = flow {
        val id = appDatabase.shoppingListDao().insert(shoppingListDbConverter.map(shoppingList))
        emitAll(
            appDatabase.shoppingListDao().getById(id)
                .mapNotNull { shoppingListDbConverter.map(it) }
        )
    }

}

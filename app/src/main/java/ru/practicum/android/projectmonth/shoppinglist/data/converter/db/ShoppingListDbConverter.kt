package ru.practicum.android.projectmonth.shoppinglist.data.converter.db

import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

class ShoppingListDbConverter {

    fun map(shoppingListEntity: ShoppingListEntity): ShoppingList {
        return ShoppingList(
            id = shoppingListEntity.id,
            name = shoppingListEntity.name,
            iconRes = shoppingListEntity.iconRes,
            products = emptyList(),
            sortType = shoppingListEntity.sortType,
            login = shoppingListEntity.login
        )
    }

    fun map(shoppingList: ShoppingList, login: String): ShoppingListEntity {
        return ShoppingListEntity(
            id = shoppingList.id,
            name = shoppingList.name,
            iconRes = shoppingList.iconRes,
            sortType = shoppingList.sortType,
            login = login
        )
    }
}

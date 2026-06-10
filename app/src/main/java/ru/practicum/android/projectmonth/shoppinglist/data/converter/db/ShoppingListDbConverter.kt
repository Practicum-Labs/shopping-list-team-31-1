package ru.practicum.android.projectmonth.shoppinglist.data.converter.db

import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.relations.ShoppingListWithProducts
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

class ShoppingListDbConverter (
    val productDbConverter: ProductDbConverter
) {

    fun map(shoppingListEntity: ShoppingListEntity): ShoppingList {
        return ShoppingList(
            id = shoppingListEntity.id,
            name = shoppingListEntity.name,
            iconRes = shoppingListEntity.iconRes,
            products = emptyList(),
            sortType = shoppingListEntity.sortType
            products = emptyList(),
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

    fun map(shoppingListWithProducts: ShoppingListWithProducts?): ShoppingList? {
        if (shoppingListWithProducts == null) return null

        val products = shoppingListWithProducts.products
            .map { productDbConverter.map(it) }

        return ShoppingList(
            id = shoppingListWithProducts.shoppingList.id,
            name = shoppingListWithProducts.shoppingList.name,
            iconRes = shoppingListWithProducts.shoppingList.iconRes,
            products = products,
            sortType = shoppingListWithProducts.shoppingList.sortType,
            login = shoppingListWithProducts.shoppingList.login
        )
    }
}

package ru.practicum.android.projectmonth.shoppinglist.data.converter.db

import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.relations.ShoppingListWithProducts
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList

class ShoppingListDbConverter (
    val productDbConverter: ProductDbConverter
) {

    fun map(shoppingListEntity: ShoppingListEntity?): ShoppingList? {
        if (shoppingListEntity == null) return null
        return ShoppingList(
            id = shoppingListEntity.id,
            name = shoppingListEntity.name,
            iconRes = shoppingListEntity.iconRes,
            products = emptyList()
        )
    }

    fun map(shoppingList: ShoppingList): ShoppingListEntity {
        return ShoppingListEntity(
            id = shoppingList.id,
            name = shoppingList.name,
            iconRes = shoppingList.iconRes
        )
    }

    fun map(shoppingListWithProducts: ShoppingListWithProducts?): ShoppingList? {
        if (shoppingListWithProducts == null) return null

        val products = shoppingListWithProducts.products
            .mapNotNull { productDbConverter.map(it) }

        return ShoppingList(
            id = shoppingListWithProducts.shoppingList.id,
            name = shoppingListWithProducts.shoppingList.name,
            iconRes = shoppingListWithProducts.shoppingList.iconRes,
            products = products
        )
    }
}
package ru.practicum.android.projectmonth.shoppinglist.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity

data class ProductWithShoppingList (

    @Embedded
    val product: ProductEntity,
    @Relation(
        parentColumn = "shoppingListId",
        entityColumn = "id",
        entity = ShoppingListEntity::class
    )
    val shoppingList: ShoppingListEntity

)

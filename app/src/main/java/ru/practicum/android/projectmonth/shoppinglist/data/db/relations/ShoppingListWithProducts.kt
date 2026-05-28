package ru.practicum.android.projectmonth.shoppinglist.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity

data class ShoppingListWithProducts (

    @Embedded
    val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopping_list_id"
    )
    val products: List<ProductEntity>

)

package ru.practicum.android.projectmonth.shoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    foreignKeys = [ForeignKey(
        entity = ShoppingListEntity::class,
        parentColumns = ["id"],
        childColumns = ["shopping_list_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("shopping_list_id")
    val shoppingListId: Long,
    val name: String,
    val checked: Boolean,
    val number: Float,
    @ColumnInfo("measure_unit")
    val measureUnit: String

)
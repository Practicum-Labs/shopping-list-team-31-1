package ru.practicum.android.projectmonth.shoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "product_suggest",
    primaryKeys = ["product_name", "user"]
)
data class ProductSuggestEntity (
    @ColumnInfo("product_name")
    val productName: String,
    val user: String
)
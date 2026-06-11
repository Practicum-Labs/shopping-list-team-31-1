package ru.practicum.android.projectmonth.shoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_suggest")
data class ProductSuggestEntity (
    @PrimaryKey
    @ColumnInfo("product_name")
    val productName: String,
    val user: String
)
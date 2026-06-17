package ru.practicum.android.projectmonth.shoppinglist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductSuggestEntity

@Dao
interface ProductSuggestDao {
    @Query("SELECT product_name FROM product_suggest WHERE user = :user ORDER BY product_name")
    fun getAll(user: String): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(productSuggest: ProductSuggestEntity)
}

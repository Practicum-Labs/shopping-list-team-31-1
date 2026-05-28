package ru.practicum.android.projectmonth.shoppinglist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE shopping_list_id = :shoppingListId")
    fun getProductsByShoppingListId(shoppingListId: Long): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id: Long): Flow<ProductEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>): List<Long>

    @Update
    suspend fun update(product: ProductEntity): Int

    @Query("DELETE FROM product WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query("DELETE FROM product WHERE shopping_list_id = :shoppingListId")
    suspend fun deleteByShoppingListId(shoppingListId: Long): Int

    @Query("DELETE FROM product")
    suspend fun deleteAll(): Int

    @Delete
    suspend fun delete(product: ProductEntity): Int

}

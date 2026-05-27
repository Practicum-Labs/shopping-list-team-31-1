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

    // Вывод всего
    @Query("SELECT * FROM product")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product")
    suspend fun getAllList(): List<ProductEntity>

    // Вывод всех продуктов для конкретного списка покупок
    @Query("SELECT * FROM product WHERE shopping_list_id = :shoppingListId")
    fun getProductsByShoppingListId(shoppingListId: Long): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE shopping_list_id = :shoppingListId")
    suspend fun getProductsByShoppingListIdSuspend(shoppingListId: Long): List<ProductEntity>

    // Вывод по id
    @Query("SELECT * FROM product WHERE id = :id")
    fun getById(id: Long): Flow<ProductEntity?>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getByIdSuspend(id: Long): ProductEntity?

    // Добавление
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg product: ProductEntity): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>): List<Long>

    // Изменение
    @Update
    suspend fun update(product: ProductEntity): Int

    @Update
    suspend fun updateAll(vararg product: ProductEntity): Int

    @Update
    suspend fun updateAll(product: List<ProductEntity>): Int

    // Удаление по id
    @Query("DELETE FROM product WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    // Удаление всех продуктов конкретного списка
    @Query("DELETE FROM product WHERE shopping_list_id = :shoppingListId")
    suspend fun deleteByShoppingListId(shoppingListId: Long): Int

    // Удаление всего
    @Query("DELETE FROM product")
    suspend fun deleteAll(): Int

    // Удаление объекта целиком
    @Delete
    suspend fun delete(product: ProductEntity): Int

    @Delete
    suspend fun deleteAll(vararg product: ProductEntity): Int

    @Delete
    suspend fun deleteAll(product: List<ProductEntity>): Int
}
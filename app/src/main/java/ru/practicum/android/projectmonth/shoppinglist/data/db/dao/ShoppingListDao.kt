package ru.practicum.android.projectmonth.shoppinglist.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.relations.ShoppingListWithProducts

@Dao
interface ShoppingListDao {

    // Вывод всего
    @Query("SELECT * FROM shopping_list")
    fun getAll(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_list")
    suspend fun getAllList(): List<ShoppingListEntity>

    // Вывод по id
    @Query("SELECT * FROM shopping_list WHERE id = :id")
    fun getById(id: Long): Flow<ShoppingListEntity?>

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    suspend fun getByIdSuspend(id: Long): ShoppingListEntity?

    // Добавление
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingList: ShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg shoppingLists: ShoppingListEntity): List<Long>

    // Изменение
    @Update
    suspend fun update(shoppingList: ShoppingListEntity): Int

    @Update
    suspend fun updateAll(vararg shoppingLists: ShoppingListEntity): Int

    // Удаление по id
    @Query("DELETE FROM shopping_list WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    // Удаление всего
    @Query("DELETE FROM shopping_list")
    suspend fun deleteAll(): Int

    // Удаление объекта целиком
    @Delete
    suspend fun delete(shoppingList: ShoppingListEntity): Int

    @Delete
    suspend fun deleteAll(vararg shoppingLists: ShoppingListEntity): Int

    @Transaction
    @Query("SELECT * FROM shopping_list")
    fun getAllWithProducts(): Flow<List<ShoppingListWithProducts>>

    @Transaction
    @Query("SELECT * FROM shopping_list WHERE id = :id")
    fun getByIdWithProducts(id: Long): Flow<ShoppingListWithProducts?>
}
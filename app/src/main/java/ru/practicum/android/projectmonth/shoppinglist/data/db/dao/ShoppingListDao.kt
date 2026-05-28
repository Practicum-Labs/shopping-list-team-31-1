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

    @Query("SELECT * FROM shopping_list")
    fun getAll(): Flow<List<ShoppingListEntity>>

    @Query("SELECT * FROM shopping_list")
    suspend fun getAllList(): List<ShoppingListEntity>

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    suspend fun getById(id: Long): Flow<ShoppingListEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingList: ShoppingListEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg shoppingLists: ShoppingListEntity): List<Long>

    @Update
    suspend fun update(shoppingList: ShoppingListEntity): Int

    @Update
    suspend fun updateAll(vararg shoppingLists: ShoppingListEntity): Int

    @Query("DELETE FROM shopping_list WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query("DELETE FROM shopping_list")
    suspend fun deleteAll(): Int

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
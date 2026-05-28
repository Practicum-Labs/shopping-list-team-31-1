package ru.practicum.android.projectmonth.shoppinglist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.projectmonth.shoppinglist.data.db.dao.ProductDao
import ru.practicum.android.projectmonth.shoppinglist.data.db.dao.ShoppingListDao
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ProductEntity
import ru.practicum.android.projectmonth.shoppinglist.data.db.entity.ShoppingListEntity


@Database(
    entities = [
        ShoppingListEntity::class,
        ProductEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun productDao(): ProductDao

}

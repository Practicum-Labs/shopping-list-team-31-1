package ru.practicum.android.projectmonth.shoppinglist.di

import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ProductDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.converter.db.ShoppingListDbConverter
import ru.practicum.android.projectmonth.shoppinglist.data.db.AppDatabase

val dataModule = module {


    // region Converters
    single {
        ShoppingListDbConverter(get())
    }
    single {
        ProductDbConverter()
    }

    factory {
        Gson()
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }
}

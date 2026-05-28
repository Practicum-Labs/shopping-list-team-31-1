package ru.practicum.android.projectmonth.shoppinglist.di

import com.google.gson.Gson
import org.koin.dsl.module
import ru.practicum.android.projectmonth.shoppinglist.data.ProductRepositoryImpl
import ru.practicum.android.projectmonth.shoppinglist.data.ShoppingListRepositoryImpl
import ru.practicum.android.projectmonth.shoppinglist.domain.ProductRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.ShoppingListRepository

val repositoryModule = module {

    factory { Gson() }


    single<ProductRepository> {
        ProductRepositoryImpl(get(), get())
    }

    single<ShoppingListRepository> {
        ShoppingListRepositoryImpl(get(), get())
    }

}

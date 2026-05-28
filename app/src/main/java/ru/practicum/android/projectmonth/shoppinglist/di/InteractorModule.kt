package ru.practicum.android.projectmonth.shoppinglist.di

import org.koin.dsl.module
import ru.practicum.android.projectmonth.shoppinglist.domain.interactor.ProductInteractorImpl
import ru.practicum.android.projectmonth.shoppinglist.domain.interactor.ShoppingListInteractorImpl
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ProductInteractor
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.ShoppingListInteractor

val interactorModule = module {
    single<ProductInteractor> {
        ProductInteractorImpl(get())
    }
    single<ShoppingListInteractor> {
        ShoppingListInteractorImpl(get())
    }
}

package ru.practicum.android.projectmonth.shoppinglist.di

import org.koin.dsl.module
import ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel.ShoppingListsViewModel

val viewModelModule = module {

    single {
        ShoppingListsViewModel(get())
    }
}

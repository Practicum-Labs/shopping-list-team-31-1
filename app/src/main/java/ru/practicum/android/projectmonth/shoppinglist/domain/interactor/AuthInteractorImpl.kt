package ru.practicum.android.projectmonth.shoppinglist.domain.interactor

import ru.practicum.android.projectmonth.shoppinglist.domain.AuthRepository
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor

class AuthInteractorImpl(
    val repository: AuthRepository
) : AuthInteractor{

}
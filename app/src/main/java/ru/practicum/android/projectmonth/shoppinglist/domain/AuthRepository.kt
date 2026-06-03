package ru.practicum.android.projectmonth.shoppinglist.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.projectmonth.shoppinglist.domain.models.LoginCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.RegisterCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.SecurityState

interface AuthRepository {

    fun registerUser(registerCredentials: RegisterCredentials): Flow<SecurityState>
    fun authenticate(loginCredentials: LoginCredentials): Flow<SecurityState>
    fun refreshAccessToken(): Flow<SecurityState>
    fun checkAuthorization(): Flow<SecurityState>


}

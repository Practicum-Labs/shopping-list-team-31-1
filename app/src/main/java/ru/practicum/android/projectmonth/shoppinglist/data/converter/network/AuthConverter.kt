package ru.practicum.android.projectmonth.shoppinglist.data.converter.network

import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.LoginRequest
import ru.practicum.android.projectmonth.shoppinglist.data.network.dto.RegistrationRequest
import ru.practicum.android.projectmonth.shoppinglist.domain.models.LoginCredentials
import ru.practicum.android.projectmonth.shoppinglist.domain.models.RegisterCredentials

class AuthConverter {
    fun map(credentials: LoginCredentials) : LoginRequest {
        return LoginRequest(
            email = credentials.email,
            password = credentials.password
        )
    }
    fun map(credentials: RegisterCredentials) : RegistrationRequest {
        return RegistrationRequest(
            email = credentials.email,
            password = credentials.password
        )
    }

}

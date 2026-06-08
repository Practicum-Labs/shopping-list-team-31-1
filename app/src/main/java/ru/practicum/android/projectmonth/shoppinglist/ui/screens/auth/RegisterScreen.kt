package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.UiSecurityState
import ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel.AuthViewModel
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.MockAuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.WarnTextField
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegistrationSuccess: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }

    var isRegistering by rememberSaveable { mutableStateOf(false) }

    var errorMsg by rememberSaveable { mutableStateOf<String?>(null) }  // ✅ сделать state

    LaunchedEffect(email, password, repeatPassword) {
        errorMsg = null
    }

    val state by viewModel.registerState.collectAsStateWithLifecycle()
    when(state) {
        is UiSecurityState.Default -> { errorMsg = null }
        is UiSecurityState.Loading -> { errorMsg = null }
        is UiSecurityState.Error -> { errorMsg = (state as UiSecurityState.Error).errMsg}
        is UiSecurityState.Success -> {
            onRegistrationSuccess()
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        CustomTextInput(
            value = email,
            onValueChange = {
                email = it
            },

            labelResId = R.string.email,
            placeholderResId = R.string.enter_email,
            modifier = Modifier.fillMaxWidth(),
            includeClearIcon = true
        )
        if (email.isEmpty()) return

        if (!viewModel.isValidEmail(email)) {
            WarnTextField(stringResource(R.string.email_wrong_format))
            return
        }
        CustomTextInput(
            value = password,
            onValueChange = {
                password = it
            },
            labelResId = R.string.password,
            placeholderResId = R.string.come_up_with_a_password,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            includeClearIcon = true
        )
        if (password.trim().isEmpty()) return
        if (password.trim().length < 6) {
            WarnTextField(stringResource(R.string.register_password_warning))
            return
        }
        CustomTextInput(
            value = repeatPassword,
            onValueChange = {
                repeatPassword = it
            },
            labelResId = R.string.repeat_password,
            placeholderResId = R.string.repeat_password,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            includeClearIcon = true

        )

        if (repeatPassword.isEmpty()) return
        if (repeatPassword != password) {
            WarnTextField(stringResource(R.string.passwords_not_equals_warning))
            return
        }

        errorMsg?.let { error ->
            WarnTextField(notificationText = error)
        }

        TextButton(
            colors = ButtonDefaults.textButtonColors(
                containerColor = BottomSheetPeach
            ),
            onClick = {
                isRegistering = true
                viewModel.register(email, password)
            }) {

            Text(
                text = stringResource(R.string.register),
                style = MaterialTheme.typography.bodyLarge
            )
        }


    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    val mockInteractor: AuthInteractor = MockAuthInteractor()
    val mockViewModel = AuthViewModel(mockInteractor)

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        RegisterScreen(
            viewModel = mockViewModel,  onRegistrationSuccess = {}
        )
    }
}


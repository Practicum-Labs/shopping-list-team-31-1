package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.UiSecurityState
import ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel.AuthViewModel
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput
import ru.practicum.android.projectmonth.shoppinglist.ui.components.MainScreenTitle
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.MockAuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.WarnTextField
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach

@Composable
fun RecoveryPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    initialEmail: String = ""
) {
    var email by rememberSaveable { mutableStateOf(initialEmail) }
    val context = LocalContext.current
    val state by viewModel.recoveryPasswdState.collectAsStateWithLifecycle()

    when (state) {
        is UiSecurityState.Default,
        is UiSecurityState.Loading -> {
        }

        is UiSecurityState.Success -> {
            Toast.makeText(
                context,
                stringResource(R.string.recovery_password_success, email),
//                (state as UiSecurityState.Success).msg,
                Toast.LENGTH_LONG
            ).show()

            navController.popBackStack()
        }
        is UiSecurityState.Error -> {
            Toast.makeText(
                context,
                (state as UiSecurityState.Error).errMsg,
                Toast.LENGTH_LONG
            ).show()
        }

    }

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.img_fogot_passwd),
            contentDescription = null,
            modifier = Modifier.padding(top = 236.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            MainScreenTitle()
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

            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    containerColor = BottomSheetPeach
                ),
                onClick = {
                    viewModel.recoveryPassword(email)

                }) {
                Text(
                    text = stringResource(R.string.recover_password),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }

    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecoveryPasswordScreenPreview() {
    val mockInteractor: AuthInteractor = MockAuthInteractor()
    val mockViewModel = AuthViewModel(mockInteractor)

    Column {
        Spacer(modifier = Modifier.height(40.dp))
        RecoveryPasswordScreen(
            viewModel = mockViewModel,
            navController = rememberNavController()
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecoveryPasswordScreenPreview2() {
    val mockInteractor: AuthInteractor = MockAuthInteractor()
    val mockViewModel = AuthViewModel(mockInteractor)

    RecoveryPasswordScreen(
        viewModel = mockViewModel,
        navController = rememberNavController(),
        initialEmail = "oleg@oleg.olegov"
    )

}

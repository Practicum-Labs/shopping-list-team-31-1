package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.core.navigation.Destination
import ru.practicum.android.projectmonth.shoppinglist.domain.usecaces.AuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel.AuthViewModel
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput
import ru.practicum.android.projectmonth.shoppinglist.ui.components.MainScreenTitle
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.MockAuthInteractor
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components.WarnTextField
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach

@Composable
fun RecoveryPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img_fogot_passwd),
            contentDescription = null,
//            modifier = Modifier.padding(horizontal = 44.dp, top = )
            modifier = Modifier.padding(top = 236.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
//                .fillMaxHeight()
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
//            if (email.isEmpty()) return
            if (!viewModel.isValidEmail(email)) {
                WarnTextField(stringResource(R.string.email_wrong_format))
//                return
            }

            val toastMsg = stringResource(R.string.recovery_password_success, email)
            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    containerColor = BottomSheetPeach
                ),
                onClick = {
                    Toast.makeText(
                        context,
                        toastMsg,
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(Destination.Auth.route)
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

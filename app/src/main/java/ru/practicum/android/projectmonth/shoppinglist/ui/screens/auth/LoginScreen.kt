package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.core.navigation.Destination
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextInput(
                value = email,
                onValueChange = { email = it},
                labelResId = R.string.email,
                placeholderResId = R.string.enter_email,
                modifier = Modifier.fillMaxWidth(),
                includeClearIcon = true
            )

            CustomTextInput(
                value = password,
                onValueChange = { password = it},
                labelResId = R.string.password,
                placeholderResId = R.string.enter_password,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                includeClearIcon = true
            )
            Spacer(modifier = Modifier.height(16.dp))

        }

        Image(
            painter = painterResource(R.drawable.img_main_screen),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 44.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {
            navController.navigate(Destination.ShoppingLists.route)
        }) {

            Text(
                text = stringResource(R.string.enter_in_shoppinglist),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        TextButton(onClick = {
            navController.navigate(Destination.RecoveryPassword.route)
        }) {

            Text(
                text = stringResource(R.string.password_recovery),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

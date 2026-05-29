package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.TopBarButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.products_screen_title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            TopBarButton(
                iconResId = R.drawable.ic_arrow_back,
                onClick = {
                    navController.navigateUp()
                }
            )
        },
        actions = {
            TopBarButton(R.drawable.ic_menu)
        },
        windowInsets = WindowInsets(0, 0, 0, 0)
    )
}
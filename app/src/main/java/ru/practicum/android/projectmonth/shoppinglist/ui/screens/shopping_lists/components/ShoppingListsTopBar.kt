package ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.TopBarButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.shopping_lists_screen_title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        actions = {
            TopBarButton(R.drawable.ic_search)
            TopBarButton(R.drawable.ic_delete)
            TopBarButton(R.drawable.ic_night_theme, enabled = false)

        },
        windowInsets = WindowInsets(0, 0, 0, 0)
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingListsTopBarPreview() {
    ShoppingListsTopBar(
        navController = rememberNavController()
    )
}

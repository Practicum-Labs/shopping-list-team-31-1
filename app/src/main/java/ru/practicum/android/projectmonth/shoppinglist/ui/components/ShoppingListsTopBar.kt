package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText

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

@Composable
fun TopBarButton(
    iconResId: Int,
    onClick: () -> Unit = { },
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null,
            tint = DarkText
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListsTopBarPreview() {
    ShoppingListsTopBar(
        navController = rememberNavController()
    )
}

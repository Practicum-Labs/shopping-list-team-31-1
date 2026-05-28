package ru.practicum.android.projectmonth.shoppinglist.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.components.ShoppingListsTopBar

@Composable
fun ShoppingListsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            ShoppingListsTopBar(
                titleResId = R.string.shopping_lists_screen_title,
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        IllustratedMessage(
            imageResId = R.drawable.img_shopping_lists,
            headerResId = R.string.shopping_lists_screen_header,
            messageResId = R.string.shopping_lists_screen_message,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingListsScreenPreview() {
    ShoppingListsScreen(
        navController = rememberNavController()
    )
}
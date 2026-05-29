package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomFab
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.ProductsTopBar

@Composable
fun ProductsScreen(
    navController: NavController,
    shoppingListId: Long = 0
) {
    Scaffold(
        topBar = {
            ProductsTopBar(
                navController = navController
            )
        },
        floatingActionButton = {
            CustomFab(
                onClick = { }
            )
        }
    ) { innerPadding ->

        IllustratedMessage(
            imageResId = R.drawable.img_products,
            headerResId = R.string.products_screen_header,
            messageResId = R.string.products_screen_message,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
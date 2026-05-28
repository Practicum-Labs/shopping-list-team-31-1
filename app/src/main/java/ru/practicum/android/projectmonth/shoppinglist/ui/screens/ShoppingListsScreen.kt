package ru.practicum.android.projectmonth.shoppinglist.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.components.NewShoppingListDialog
import ru.practicum.android.projectmonth.shoppinglist.ui.components.ShoppingListsTopBar

@Composable
fun ShoppingListsScreen(
    navController: NavController
) {
    var showAddingDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ShoppingListsTopBar(
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddingDialog = true
                }
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

        if (showAddingDialog) {
            NewShoppingListDialog(
                onDismissRequest = {
                    showAddingDialog = false
                },
                onConfirm = { }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShoppingListsScreenPreview() {
    ShoppingListsScreen(
        navController = rememberNavController()
    )
}
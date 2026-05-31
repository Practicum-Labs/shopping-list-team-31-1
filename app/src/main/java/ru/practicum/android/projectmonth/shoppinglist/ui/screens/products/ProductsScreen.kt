package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomFab
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.AddProductBottomSheet
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.ProductsTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    navController: NavController,
    shoppingListId: Long = 0
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    var showAddingBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ProductsTopBar(
                navController = navController
            )
        },
        floatingActionButton = {
            CustomFab(
                onClick = {
                    showAddingBottomSheet = true
                }
            )
        }
    ) { innerPadding ->

        IllustratedMessage(
            imageResId = R.drawable.img_products,
            headerResId = R.string.products_screen_header,
            messageResId = R.string.products_screen_message,
            modifier = Modifier.padding(innerPadding)
        )

        if (showAddingBottomSheet) {
            AddProductBottomSheet(
                bottomSheetState = bottomSheetState,
                onItemAdded = { name, number, unit -> { } },
                onDismissRequest = { showAddingBottomSheet = false }
            )
        }
    }
}

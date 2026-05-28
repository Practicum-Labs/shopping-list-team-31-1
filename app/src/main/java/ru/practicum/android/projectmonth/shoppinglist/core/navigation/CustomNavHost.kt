package ru.practicum.android.projectmonth.shoppinglist.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.MainScreen
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.ShoppingListsScreen

@Composable
fun CustomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable(Destination.Main.route) {
            MainScreen(
                navController = navController
            )
        }
        composable(Destination.ShoppingLists.route) {
            ShoppingListsScreen(
                navController = navController,
                viewModel = koinViewModel()
            )
        }
    }
}

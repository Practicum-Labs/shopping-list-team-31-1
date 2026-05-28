package ru.practicum.android.projectmonth.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.presentation.viewmodel.ShoppingListsViewModel
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.components.NewShoppingListDialog
import ru.practicum.android.projectmonth.shoppinglist.ui.components.ShoppingListsTopBar
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.presentation.state.ShoppingListsState
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownElements
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText

@Composable
fun ShoppingListsScreen(
    navController: NavController,
    viewModel: ShoppingListsViewModel
) {
    val uiState = viewModel.uiState
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

        when (uiState) {
            is ShoppingListsState.Empty -> {
                IllustratedMessage(
                    imageResId = R.drawable.img_shopping_lists,
                    headerResId = R.string.shopping_lists_screen_header,
                    messageResId = R.string.shopping_lists_screen_message,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            is ShoppingListsState.Content -> {
                ShoppingListsContent(
                    shoppingLists = uiState.data,
                    paddingValues = innerPadding
                )
            }
        }

        if (showAddingDialog) {
            NewShoppingListDialog(
                onDismissRequest = {
                    showAddingDialog = false
                },
                onConfirm = { newShoppingListName ->
                    viewModel.newShoppingList(
                        name = newShoppingListName
                    )
                    showAddingDialog = false
                }
            )
        }
    }
}

@Composable
fun ShoppingListsContent(
    shoppingLists: List<ShoppingList>,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = paddingValues
    ) {
        items(
            count = shoppingLists.size,
            key = { index -> shoppingLists[index].id }
        ) { index ->
            ShoppingListsItem(item = shoppingLists[index])
        }
    }
}

@Composable
fun ShoppingListsItem(item: ShoppingList) {

    val context = LocalContext.current
    val resources = LocalResources.current

    // Плохая практика, нужно будет написать специальный класс для получения id ресурса по ключу
    val iconResId = remember(resources, item.iconRes) {
        resources.getIdentifier(
            item.iconRes,
            "drawable",
            context.packageName
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .background(
                color = LightBackground,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = LightBrownElements,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = null,
                tint = MediumDarkText,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.name,
            style = MaterialTheme.typography.labelLarge,
            color = MediumDarkText
        )
    }
}
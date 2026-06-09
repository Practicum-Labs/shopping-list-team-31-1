package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R

// Варианты сортировки
enum class SortType {
    ALPHABETICAL,
    CUSTOM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsMenu(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    currentSortType: SortType,
    onSortTypeSelected: (SortType) -> Unit,
    onDeleteAllClick: () -> Unit,
    onClearPurchasedClick: () -> Unit
) {
    // Состояние видимости всплывающего меню сортировки
    var isSortMenuExpanded by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(modifier = Modifier.padding(bottom = 24.dp)) {

            // 1. Пункт "Сортировка" (Контейнер для DropdownMenu)
            Box {
                val sortLabel = when (currentSortType) {
                    SortType.ALPHABETICAL -> stringResource(R.string.products_menu_sort_alphabetical)
                    SortType.CUSTOM -> stringResource(R.string.products_menu_sort_custom)
                }

                ListItem(
                    headlineContent = { Text(stringResource(R.string.products_menu_sort)) },
                    supportingContent = { Text(sortLabel) },
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.ic_sort), // Замените на свою иконку
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.clickable { isSortMenuExpanded = true }
                )

                // Всплывающее меню вариантов сортировки
                DropdownMenu(
                    expanded = isSortMenuExpanded,
                    onDismissRequest = { isSortMenuExpanded = false }
                ) {
                    // Вариант: По алфавиту
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.products_menu_sort_alphabetical)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_sort_alpha),
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            RadioButton(
                                selected = (currentSortType == SortType.ALPHABETICAL),
                                onClick = {
                                    onSortTypeSelected(SortType.ALPHABETICAL)
                                    isSortMenuExpanded = false
                                }
                            )
                        },
                        onClick = {
                            onSortTypeSelected(SortType.ALPHABETICAL)
                            isSortMenuExpanded = false
                        }
                    )

                    // Вариант: Пользовательская
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.products_menu_sort_custom)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_sort_custom),
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            RadioButton(
                                selected = (currentSortType == SortType.CUSTOM),
                                onClick = {
                                    onSortTypeSelected(SortType.CUSTOM)
                                    isSortMenuExpanded = false
                                }
                            )
                        },
                        onClick = {
                            onSortTypeSelected(SortType.CUSTOM)
                            isSortMenuExpanded = false
                        }
                    )
                }
            }

            // 2. Пункт "Удалить"
            ListItem(
                headlineContent = { Text(stringResource(R.string.products_menu_delete_all)) },
                leadingContent = {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable {
                    onDeleteAllClick()
                    onDismissRequest()
                }
            )

            // 3. Пункт "Очистить купленные"
            ListItem(
                headlineContent = { Text(stringResource(R.string.products_menu_clear_purchased)) },
                leadingContent = {
                    Icon(
                        painter = painterResource(R.drawable.ic_clear),
                        contentDescription = null
                    )
                },
                modifier = Modifier.clickable {
                    onClearPurchasedClick()
                    onDismissRequest()
                }
            )
        }
    }
}

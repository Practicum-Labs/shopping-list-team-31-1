package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomFab
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.AddProductBottomSheet
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.ProductsTopBar
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    navController: NavController,
    shoppingListId: Long = 0
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    val isBottomSheetVisible = scaffoldState.bottomSheetState.targetValue == SheetValue.Expanded

    // Затемнение фона при открытии диалога
    val backgroundAlfa by remember(isBottomSheetVisible) {
        derivedStateOf {
            if (isBottomSheetVisible) {
                scaffoldState.bottomSheetState.requireOffset().coerceIn(0f, 1f)
            } else 0f
        }
    }

    // Размеры верхней панели для отдельного затемнения
    var topBarSize by remember { mutableStateOf(IntSize.Zero) }


    // Смещение кнопки вверх при открытии диалога
    val fabOffset by remember(isBottomSheetVisible) {
        derivedStateOf {
            if (isBottomSheetVisible) {
                scaffoldState.bottomSheetState.requireOffset()
            } else 0f
        }
    }

    Box(Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            topBar = {
                ProductsTopBar(
                    navController = navController,
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        topBarSize = coordinates.size
                    }
                )

                // Затемнение верхней панели
                if (backgroundAlfa > 0f) {
                    // TODO: Вынести в отдельную функцию
                    Box(
                        modifier = Modifier
                            .width(topBarSize.width.dp)
                            .height(topBarSize.height.dp)
                            .background(Color.Black.copy(alpha = backgroundAlfa * 0.6f))
                            // Обработка кликов по фону при открытом диалоге
                            .pointerInput(Unit) {
                                detectTapGestures(onTap = {
                                    scope.launch { scaffoldState.bottomSheetState.hide() }
                                })
                            }
                    )
                }
            },
            sheetContent = {
                AddProductBottomSheet(
                    onValuesChange = { name, number, unit -> { } }
                )
            },
            scaffoldState = scaffoldState,
            sheetContainerColor = BottomSheetPeach,
            sheetPeekHeight = 0.dp
        ) { innerPadding ->

            IllustratedMessage(
                imageResId = R.drawable.img_products,
                headerResId = R.string.products_screen_header,
                messageResId = R.string.products_screen_message,
                modifier = Modifier.padding(innerPadding)
            )

            // Затемнение фона
            if (backgroundAlfa > 0f) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = backgroundAlfa * 0.6f))
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                scope.launch { scaffoldState.bottomSheetState.hide() }
                            })
                        }
                )
            }
        }

        CustomFab(
            onClick = {
                scope.launch {
                    if (isBottomSheetVisible) {
                        scaffoldState.bottomSheetState.hide()
                    } else {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            },
            iconResId = if (isBottomSheetVisible) R.drawable.ic_confirm else R.drawable.ic_add,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .offset {
                    if (fabOffset > 0) {
                        IntOffset(0, -(fabOffset * 0.3f).toInt())
                    } else {
                        IntOffset(0, 0)
                    }
                }
        )
    }
}


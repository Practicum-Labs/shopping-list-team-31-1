package ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.ShoppingListsItem
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownElements
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.RegularBrown
import kotlin.math.abs
import android.widget.Toast
import androidx.compose.foundation.shape.CircleShape

@Composable
fun SwipeableShoppingListItem(
    item: ShoppingList,
    onItemClick: (ShoppingList) -> Unit,
    onDelete: (ShoppingList) -> Unit,
    onRename: (ShoppingList) -> Unit,
    onCopy: (ShoppingList) -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Ширина для 3 иконок в кружочках (каждая ~56dp с отступами)
    val iconsWidth = with(LocalDensity.current) { 200.dp.toPx() }
    val swipeThreshold = with(LocalDensity.current) { 50.dp.toPx() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Фоновые действия (светлый фон, который появляется при свайпе)
        Row(
            modifier = Modifier
                .matchParentSize()
                .background(LightBrownElements),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Кнопка редактирования - белый кружок
            IconButton(
                onClick = {
                    scope.launch {
                        onRename(item)
                        offsetX = 0f
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(LightBackground, CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Редактировать",
                    tint = MediumDarkText,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопка копирования - белый кружок (заглушка)
            IconButton(
                onClick = {
                    scope.launch {
                        Toast.makeText(context, "Функция копирования временно недоступна", Toast.LENGTH_SHORT).show()
                        offsetX = 0f
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(LightBackground, CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_copy),
                    contentDescription = "Копировать",
                    tint = MediumDarkText,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Кнопка удаления - белый кружок
            IconButton(
                onClick = {
                    scope.launch {
                        onDelete(item)
                        offsetX = 0f
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(LightBackground, CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Удалить",
                    tint = RegularBrown,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
        }

        // Основной контент (перемещаемый)
        ShoppingListsItem(
            item = item,
            onClick = onItemClick,
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetX.toInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            scope.launch {
                                if (abs(offsetX) > swipeThreshold) {
                                    offsetX = -iconsWidth
                                } else {
                                    offsetX = 0f
                                }
                            }
                        },
                        onDragCancel = {
                            scope.launch {
                                offsetX = 0f
                            }
                        },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            val newOffset = offsetX + dragAmount
                            offsetX = when {
                                newOffset > 0 -> 0f
                                newOffset < -iconsWidth -> -iconsWidth
                                else -> newOffset
                            }
                        }
                    )
                }
        )
    }
}
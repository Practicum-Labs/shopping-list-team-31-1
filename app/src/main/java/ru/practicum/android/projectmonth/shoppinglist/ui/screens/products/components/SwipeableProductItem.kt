package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.domain.models.ShoppingList
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.ShoppingListsItem
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownElements
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.RegularBrown
import kotlin.math.abs

@Composable
fun SwipeableProductItem(
    item: Product,
    onCheckedChange: (Boolean) -> Unit,
    onProductChange: (Product) -> Unit,
    onProductDelete: (Product) -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    // Ширина для 2 иконок в кружочках (каждая ~56dp с отступами)
    val iconsWidth = with(LocalDensity.current) { 128.dp.toPx() }
    val swipeThreshold = with(LocalDensity.current) { 50.dp.toPx() }

    Box(
        modifier = Modifier
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
            // Кнопка редактирования
            RoundIconButton(
                iconResId = R.drawable.ic_edit,
                onClick = {
                    scope.launch {
                        onProductChange(item)
                        offsetX = 0f
                    }
                }
            )

            Spacer(modifier = Modifier.width(4.dp))

            // Кнопка удаления
            RoundIconButton(
                iconResId = R.drawable.ic_delete,
                onClick = {
                    scope.launch {
                        onProductDelete(item)
                        offsetX = 0f
                    }
                }
            )

            Spacer(modifier = Modifier.width(16.dp))
        }

        // Основной контент (перемещаемый)
        ProductItem(
            item = item,
            onCheckedChange = onCheckedChange,
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

@Composable
fun ProductItem(
    item: Product,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 16.dp)
        )
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.labelLarge,
                color = DarkText
            )
            Text(
                text = "${trimInteger(item.number)} ${item.measureUnit}",
                style = MaterialTheme.typography.bodyMedium,
                color = MediumDarkText
            )
        }
    }
}
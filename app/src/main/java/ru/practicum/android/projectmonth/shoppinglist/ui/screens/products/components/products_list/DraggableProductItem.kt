package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.products_list

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText

@Composable
fun DraggableProductItem(
    product: Product,
    onCheckedChange: (Boolean) -> Unit,
    onProductChange: (Product) -> Unit,
    onProductDelete: (Product) -> Unit,
    onDragStart: () -> Unit,
    onDragEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetY by remember { mutableFloatStateOf(0f) }
    val dragState = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(LightBackground)
    ) {
        // Основной контент (Checkbox + текст)
        ProductItem(
            item = product,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.fillMaxWidth()
        )

        // Иконка drag handle справа
        Icon(
            painter = painterResource(R.drawable.ic_drag_handle),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            onDragStart()
                            dragState.value = true
                        },
                        onDragEnd = {
                            onDragEnd()
                            dragState.value = false
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            offsetY += dragAmount.y
                        }
                    )
                },
            tint = MediumDarkText
        )
    }
}
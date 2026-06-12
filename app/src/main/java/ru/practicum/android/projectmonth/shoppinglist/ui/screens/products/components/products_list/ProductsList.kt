package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.products_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.domain.models.Product
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.productListDividerColor

@Composable
fun ProductsList(
    products: List<Product>,
    onCheckedChange: (Product, Boolean) -> Unit,
    onProductChange: (Product) -> Unit,
    onProductDelete: (Product) -> Unit
) {
    LazyColumn {
        items(
            items = products,
            key = { it.id }
        ) { product ->

            SwipeableProductItem(
                item = product,
                onCheckedChange = { isChecked ->
                    onCheckedChange(product, isChecked)
                },
                onProductChange = {
                    onProductChange(product)
                },
                onProductDelete = {
                    onProductDelete(product)
                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = productListDividerColor
            )
        }
    }
}
package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBottomSheet(
    bottomSheetState: SheetState,
    onItemAdded: (name: String, number: String, unit: String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("шт") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val measureUnits = stringArrayResource(R.array.measure_units)

    var currentNumber = number.toFloatOrNull() ?: 0f
    var minusButtonEnabled = currentNumber > 1

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Поле ввода названия товара
            CustomTextInput(
                value = productName,
                onValueChange = { productName = it },
                labelResId = R.string.products_new_textfield_label,
                placeholderResId = R.string.products_new_textfield_placeholder,
                modifier = Modifier.fillMaxWidth()
            )

            // Нижний ряд элементов
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Поле ввода количества
                CustomTextInput(
                    value = number,
                    onValueChange = { number = it },
                    labelResId = R.string.products_new_textfield_number,
                    placeholderResId = R.string.products_new_textfield_number,
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Выпадающий список единиц измерения
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedUnit,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                        modifier = Modifier.menuAnchor(
                            type = ExposedDropdownMenuAnchorType.PrimaryNotEditable
                        ),
                        shape = RoundedCornerShape(8.dp),
                    )
                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        measureUnits.forEach { unit ->
                            DropdownMenuItem(
                                text = { Text(unit) },
                                onClick = {
                                    selectedUnit = unit
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Кнопка минус
                IconButton(
                    onClick = {
                        number = (currentNumber - 1).toString()
                    },
                    enabled = minusButtonEnabled,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.LightGray.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier.size(48.dp)
                ) {
                    Text("—", style = MaterialTheme.typography.titleMedium)
                }

                // Кнопка плюс (добавление)
                IconButton(
                    onClick = {
                        if (productName.isNotBlank()) {
                            onItemAdded(productName, number, selectedUnit)
                            productName = ""
                            number = ""
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.LightGray.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier.size(48.dp)
                ) {
                    Text("+", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddProductBottomSheetPreview() {
    AddProductBottomSheet(
        bottomSheetState = rememberModalBottomSheetState(),
        onItemAdded = { name, number, unit -> { } },
        onDismissRequest = { }
    )
}
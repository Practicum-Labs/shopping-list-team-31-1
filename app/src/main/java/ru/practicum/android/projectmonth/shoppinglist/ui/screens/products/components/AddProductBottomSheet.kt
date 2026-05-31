package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.CustomTextInput
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText

// Используется только здесь, нет необходимости выносить в тему
val measureUnitsDropdownColor = Color(0xFFFAEBE0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBottomSheet(
    onValuesChanged: (productName: String, number: String, measureUnit: String) -> Unit
) {
    val measureUnits = stringArrayResource(R.array.measure_units)

    var productName by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val currentNumber = number.toFloatOrNull() ?: 0f
    val minusButtonEnabled = currentNumber >= 1

    Surface(
        color = BottomSheetPeach,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Поле ввода названия товара
            CustomTextInput(
                value = productName,
                onValueChange = {
                    productName = it
                    onValuesChanged(productName, number, selectedUnit)
                },
                labelResId = R.string.products_new_textfield_label,
                placeholderResId = R.string.products_new_textfield_placeholder,
                modifier = Modifier.fillMaxWidth()
            )

            // Нижний ряд элементов
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                // Поле ввода количества
                CustomTextInput(
                    value = number,
                    onValueChange = {
                        number = it
                        onValuesChanged(productName, number, selectedUnit)
                    },
                    labelResId = R.string.products_new_textfield_number,
                    placeholderResId = R.string.products_new_textfield_number,
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Выпадающий список единиц измерения
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedUnit,
                        onValueChange = {
                            onValuesChanged(productName, number, selectedUnit)
                        },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.measure_placeholder),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MediumDarkText
                            )
                        },
                        modifier = Modifier.menuAnchor(
                            type = ExposedDropdownMenuAnchorType.PrimaryNotEditable
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                        containerColor = measureUnitsDropdownColor
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
                RoundIconButton(
                    onClick = {
                        number = trimInteger(currentNumber - 1)
                    },
                    iconResId = R.drawable.ic_remove,
                    enabled = minusButtonEnabled
                )

                // Кнопка плюс
                RoundIconButton(
                    onClick = {
                        number = trimInteger(currentNumber + 1)
                    },
                    iconResId = R.drawable.ic_add
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddProductBottomSheetPreview() {
    AddProductBottomSheet(
        onValuesChanged = { name, number, unit -> { } }
    )
}

// Обрезать .0 для целого количества товаров
private fun trimInteger(digit: Float): String {
    return if (digit % 1.0 == 0.0) digit.toInt().toString() else digit.toString()
}

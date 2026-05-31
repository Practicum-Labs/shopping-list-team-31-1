package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText

@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelResId: Int,
    @StringRes placeholderResId: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Unspecified),
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(stringResource(labelResId))
        },
        placeholder = {
            Text(
                text = stringResource(placeholderResId),
                color = MediumDarkText
            )
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}
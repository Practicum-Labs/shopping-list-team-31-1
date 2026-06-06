package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.MediumDarkText

@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelResId: Int,
    @StringRes placeholderResId: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Unspecified)
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
        modifier = modifier.height(64.dp)
    )
}

@Preview
@Composable
fun CustomTextInputPreview() {
    CustomTextInput(
        value = "Текст",
        onValueChange = {},
        labelResId = R.string.email,
        placeholderResId = R.string.enter_email,
        modifier = Modifier.padding()
            .background(Color.White)
            .padding(vertical = 2.dp)
    )

}

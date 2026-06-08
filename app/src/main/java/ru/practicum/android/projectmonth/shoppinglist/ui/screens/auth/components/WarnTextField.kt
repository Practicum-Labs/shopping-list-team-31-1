package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.WarnRed

@Composable
fun WarnTextField(
    notificationText: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = notificationText,
        fontSize = 12.sp,
        color = WarnRed
    )

}

@Preview
@Composable
fun WarnTextFieldPreview(){
    WarnTextField(
        notificationText = "Тестовое предупреждение",
        modifier = Modifier.height(16.dp)
        )
}
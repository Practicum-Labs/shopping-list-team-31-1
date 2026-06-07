package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.WarnRed

@Composable
fun WarnTextField(notificationText: String) {
    Text(
        text = notificationText,
        fontSize = 12.sp,
        color = WarnRed
    )

}
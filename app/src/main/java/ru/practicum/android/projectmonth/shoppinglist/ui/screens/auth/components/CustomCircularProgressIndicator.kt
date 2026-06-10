package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach

@Composable
fun CustomCircularProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .padding(16.dp)
            .height(48.dp)
            .fillMaxWidth(),
        color = BottomSheetPeach
    )

}

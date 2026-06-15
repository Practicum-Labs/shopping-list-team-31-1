package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.R

@Composable
fun MainScreenTitle() {

    Row(
        modifier = Modifier
            .height(74.dp)
            .padding(horizontal = 36.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .offset(y = (-15).dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_main_screen_title),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

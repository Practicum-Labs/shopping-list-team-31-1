package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.practicum.android.projectmonth.shoppinglist.R

@Composable
fun CustomFab(
    onClick: () -> Unit = { },
    @DrawableRes iconResId: Int = R.drawable.ic_add
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = null
        )
    }
}
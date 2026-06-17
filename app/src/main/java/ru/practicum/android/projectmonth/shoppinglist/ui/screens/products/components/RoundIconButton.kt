package ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownElements

// Используется только здесь, нет необходимости выносить в тему
val disabledRoundButtonColor = Color(0xFFE4D7CD)
val disabledRoundButtonIconColor = Color(0xFF9C8E81)

@Composable
fun RoundIconButton(
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(
        containerColor = LightBrownElements,
        contentColor = DarkText,
        disabledContainerColor = disabledRoundButtonColor,
        disabledContentColor = disabledRoundButtonIconColor
    )
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            painterResource(iconResId),
            contentDescription = null
        )
    }
}

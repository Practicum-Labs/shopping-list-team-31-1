package ru.practicum.android.projectmonth.shoppinglist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText

@Composable
fun IllustratedMessage(
    imageResId: Int,
    headerResId: Int,
    messageResId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 44.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null
            )
            Text(
                text = stringResource(id = headerResId),
                style = MaterialTheme.typography.bodyLarge,
                color = DarkText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = stringResource(id = messageResId),
                style = MaterialTheme.typography.bodyMedium,
                color = DarkText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}
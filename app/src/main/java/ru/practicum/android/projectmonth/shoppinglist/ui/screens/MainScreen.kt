package ru.practicum.android.projectmonth.shoppinglist.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MainScreenTitle()

        IllustratedMessage(
            imageResId = R.drawable.img_main_screen,
            headerResId = R.string.main_screen_header,
            messageResId = R.string.main_screen_message
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenTitle() {

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .height(74.dp)
            .padding(horizontal = 36.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            tint = DarkText,
            modifier = Modifier
                .offset(y = (-15).dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_main_screen_title),
            contentDescription = null,
            tint = DarkText
        )
    }
}
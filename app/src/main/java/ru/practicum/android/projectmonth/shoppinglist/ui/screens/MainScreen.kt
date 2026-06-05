package ru.practicum.android.projectmonth.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.core.navigation.Destination
import ru.practicum.android.projectmonth.shoppinglist.ui.components.IllustratedMessage
import ru.practicum.android.projectmonth.shoppinglist.ui.screens.products.components.measureUnitsDropdownColor
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground


val YsDisplayRegular = FontFamily(
    Font(R.font.ys_display_regular)
)
@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        MainScreenTitle()

        AuthAndRegisterViewPager(navController)

    }
}

@Composable
fun AuthAndRegisterViewPager(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val coroutineScope = rememberCoroutineScope()

    SecondaryTabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = DarkText,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(pagerState.currentPage),
                height = 2.dp,
                color = DarkText
            )
        }
    ) {
        listOf(
            stringResource(R.string.login),
            stringResource(R.string.register)
        ).forEachIndexed { index, title ->
            Tab(
                modifier = Modifier.background(LightBackground),
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = title,
                        color = if (pagerState.currentPage == index)
                            DarkText
                        else
                            DarkText.copy(alpha = 0.7f),
                        fontFamily = YsDisplayRegular,
                        fontSize = 14.sp
                    )
                }
            )
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> LoginScreen(navController)      // Создайте этот компонент
            1 -> RegisterScreen()   // Создайте этот компонент
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    IllustratedMessage(
        imageResId = R.drawable.img_main_screen,
        headerResId = R.string.main_screen_header,
        messageResId = R.string.main_screen_message,
        modifier = Modifier.clickable {
            navController.navigate(Destination.ShoppingLists.route)
        }

    )

}

@Composable
fun RegisterScreen() {

}


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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}

package ru.practicum.android.projectmonth.shoppinglist.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.components.MainScreenTitle
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBackground


val YsDisplayRegular = FontFamily(
    Font(R.font.ys_display_regular)
)

@Composable
fun MainAuthScreen(
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box {
            MainScreenTitle()
            AuthViewPager(navController)
        }

    }
}

@Composable
fun AuthViewPager(navController: NavController) {

    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val toastMsg = stringResource(R.string.register_success)

    val onRegistrationSuccess: () -> Unit = {
        Toast.makeText(
            context,
            toastMsg,
            Toast.LENGTH_LONG
        ).show()

        // Переключаемся на 0-ю вкладку (Login)
        coroutineScope.launch {
            pagerState.animateScrollToPage(0)
        }
    }

    Column(
        modifier = Modifier.padding(top = 48.dp)
    ) {
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
                0 -> LoginScreen(navController, koinViewModel())
                1 -> RegisterScreen(
                    koinViewModel(),
                    onRegistrationSuccess = onRegistrationSuccess
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainAuthScreenPreview() {
    MainAuthScreen(navController = rememberNavController())
}

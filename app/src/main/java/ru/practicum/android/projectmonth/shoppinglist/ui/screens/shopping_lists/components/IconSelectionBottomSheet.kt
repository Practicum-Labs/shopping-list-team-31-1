package ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.domain.models.AppIcon
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.BottomSheetPeach
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownElements
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.DarkText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconSelectionBottomSheet(
    currentIconDbKey: String,
    onIconSelected: (AppIcon) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = BottomSheetPeach,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
        ) {
            // Отдельный слой для иконок с отступом от левого края 24px
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 54.dp, end = 54.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(AppIcon.entries.size) { index ->
                        val icon = AppIcon.entries[index]
                        val isSelected = icon.dbKey == currentIconDbKey

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    color = LightBrownElements,
                                    shape = CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        onIconSelected(icon)
                                        onDismiss()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(icon.resId),
                                contentDescription = icon.name,
                                tint = if (isSelected) DarkText else DarkText.copy(alpha = 0.4f),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
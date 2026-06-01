package ru.practicum.android.projectmonth.shoppinglist.ui.screens.shopping_lists.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.projectmonth.shoppinglist.R
import ru.practicum.android.projectmonth.shoppinglist.ui.theme.LightBrownSurface

@Composable
fun DeleteConfirmationDialog(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_attention),
                contentDescription = null
            )
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.delete),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        containerColor = LightBrownSurface
    )
}

@Composable
fun DeleteAllListsDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    DeleteConfirmationDialog(
        title = stringResource(R.string.delete_all_lists_title),
        onDismiss = onDismiss,
        onConfirm = onConfirm
    )
}

@Composable
fun DeleteListDialog(
    listName: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_attention),
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.delete_list_title, listName),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.delete),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        containerColor = LightBrownSurface
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteConfirmationDialogPreview() {
    DeleteConfirmationDialog(
        title = "Удалить все списки?",
        onDismiss = {},
        onConfirm = {}
    )
}
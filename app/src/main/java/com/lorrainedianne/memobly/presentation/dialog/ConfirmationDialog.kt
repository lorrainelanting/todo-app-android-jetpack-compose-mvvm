package com.lorrainedianne.memobly.presentation.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.lorrainedianne.memobly.R

@Composable
fun ConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogMessage: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.icon_warning)
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogMessage)
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.dismiss))
            }
        },
    )
}
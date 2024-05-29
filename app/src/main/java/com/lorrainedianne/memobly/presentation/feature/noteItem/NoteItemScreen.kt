package com.lorrainedianne.memobly.presentation.feature.noteItem

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorrainedianne.memobly.R
import com.lorrainedianne.memobly.presentation.dialog.ConfirmationDialog
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel

/** Screen for creating a note. **/
@Composable
fun NoteItemScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel
) {
    val noteItemVM: NoteItemViewModel = hiltViewModel()
    val noteItemState by noteItemVM.uiState.collectAsState()

    LaunchedEffect(Unit) {
        noteItemVM.onEvent(NoteItemEventType.Start)
    }

    BackHandler(true) {
        noteItemVM.onEvent(NoteItemEventType.BackPressed)
    }

    when (noteItemState) {
        is NoteItemState.Error -> {
            if (noteItemVM.isDialogOpen.value) {
                ConfirmationDialog(
                    onDismissRequest = { noteItemVM.onEvent(NoteItemEventType.DismissDialog) },
                    onConfirmation = { noteItemVM.onEvent(NoteItemEventType.ConfirmDialog) },
                    dialogTitle = stringResource(id = R.string.warning),
                    dialogMessage = (noteItemState as NoteItemState.Error).message,
                    icon = Icons.Default.Warning
                )
            }
        }

        is NoteItemState.FinishLoading -> {
            ConstraintLayout(modifier = modifier) {
                val (title, content) = createRefs()

                Title(viewModel = noteItemVM, modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                })

                Content(viewModel = noteItemVM, modifier = Modifier.constrainAs(content) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom)
                })
            }
        }

        is NoteItemState.FinishSaving -> {
            Text(text = "Finish Saving...", modifier = modifier)
        }

        is NoteItemState.Loading -> {
            Text(text = "Loading...", modifier = modifier)
        }

        is NoteItemState.Saving -> {
            Text(text = "Saving...", modifier = modifier)
        }

        is NoteItemState.Start -> {
            Text(text = "Start", modifier = modifier)
        }
    }
}

@Composable
private fun Title(viewModel: NoteItemViewModel, modifier: Modifier) {
    TextField(
        value = viewModel.titleState.value,
        onValueChange = { viewModel.onEvent(NoteItemEventType.TitleChanged(it)) },
        placeholder = { Text(text = "Title") },
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontWeight = FontWeight.SemiBold)
    )
}

@Composable
private fun Content(viewModel: NoteItemViewModel, modifier: Modifier) {
    TextField(
        value = viewModel.contentState.value,
        onValueChange = { viewModel.onEvent(NoteItemEventType.ContentChanged(it)) },
        placeholder = { Text(text = "Start typing") },
        modifier = modifier.fillMaxSize()
    )
}
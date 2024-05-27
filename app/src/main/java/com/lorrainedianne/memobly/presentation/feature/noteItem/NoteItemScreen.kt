package com.lorrainedianne.memobly.presentation.feature.noteItem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel

/** Screen for creating and editing a note. **/
@Composable
fun NoteItemScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    onBackPressed: (() -> Unit) -> Unit
) {
    // todo: remove FAB and bottom nav bar.

    val noteItemVM: NoteItemViewModel = hiltViewModel()
    val noteItemState by noteItemVM.uiState.collectAsState()

    val titleValue = remember { mutableStateOf("") }
    val contentValue = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        noteItemVM.onEvent(NoteItemEventType.Start)

        onBackPressed {
            val note = Note(title = titleValue.value, note = contentValue.value, type = "text")
            noteItemVM.onEvent(NoteItemEventType.Save(note))
        }
    }

    when (noteItemState) {
        is NoteItemState.Error -> {
            Text(text = (noteItemState as NoteItemState.Error).message, modifier = modifier)
        }

        is NoteItemState.FinishLoading -> {
            ConstraintLayout(modifier = modifier) {
                val (title, content) = createRefs()

                Title(title = titleValue, modifier = Modifier.constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                })

                Content(content = contentValue, modifier = Modifier.constrainAs(content) {
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
private fun Title(title: MutableState<String>, modifier: Modifier) {
    TextField(
        value = title.value,
        onValueChange = { title.value = it },
        placeholder = { Text(text = "Title") },
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontWeight = FontWeight.SemiBold)
    )
}

@Composable
private fun Content(content: MutableState<String>, modifier: Modifier) {
    TextField(
        value = content.value,
        onValueChange = { content.value = it },
        placeholder = { Text(text = "Start typing") },
        modifier = modifier.fillMaxSize()
    )
}
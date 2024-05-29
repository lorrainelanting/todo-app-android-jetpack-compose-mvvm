package com.lorrainedianne.memobly.presentation.feature.notes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.presentation.feature.main.MainEventType
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel
import com.lorrainedianne.memobly.presentation.theme.Purple80

/** This will be the Home Screen of the app.
 * This screen will show all the list of notes. **/
@Composable
fun NotesScreen(
    notesVm: NotesViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    modifier: Modifier,
    onClickFab: (() -> Unit) -> Unit
) {
    val notesState by notesVm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        notesVm.onEvent(NotesEventType.Start)

        onClickFab {
            mainViewModel.onEvent(MainEventType.NavigateToNoteItem())
        }
    }

    when (notesState) {
        is NotesState.Loading -> {
            Text(text = "Loading...", modifier = modifier)
        }

        is NotesState.FinishLoading -> {
            ConstraintLayout(modifier = modifier) {
                val (finishLoading, noteList) = createRefs()

                Text(text = "Finish loading", modifier = Modifier.constrainAs(finishLoading) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                })

                NoteList(notesVm, modifier = Modifier.constrainAs(noteList) {
                    start.linkTo(parent.start)
                    top.linkTo(finishLoading.bottom, margin = 8.dp)
                })
            }
        }

        is NotesState.Error -> {
            Text(text = (notesState as NotesState.Error).message, modifier = modifier)
        }

        is NotesState.Start -> {
            Text(text = "Start", modifier = modifier)
        }
    }

}

@Composable
fun NoteList(vm: NotesViewModel, modifier: Modifier) {
    val data: List<Note> = vm.notesFlow.collectAsState().value
    Log.d("DATA_DEBUG", "NOTES_SCREEN LIST: $data")
    if (data.isEmpty()) {
        Text(text = "Note list is empty", modifier = modifier)
    } else {
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(data.size) { index ->
                val note: Note = data[index]
                NoteItem(note)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note) {
    Column(
        modifier = Modifier
            .background(Purple80)
            .height(50.dp)
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = note.title ?: "",
            fontWeight = FontWeight.Bold,
            minLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = note.note ?: "", minLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

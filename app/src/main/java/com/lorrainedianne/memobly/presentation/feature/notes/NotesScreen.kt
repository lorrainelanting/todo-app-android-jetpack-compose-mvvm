package com.lorrainedianne.memobly.presentation.feature.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorrainedianne.memobly.domain.model.Note

@Composable
fun NotesScreen(notesVm: NotesViewModel = hiltViewModel(), modifier: Modifier) {
    val notesState by notesVm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        notesVm.onEvent(NotesEventType.Start)
    }

    when(notesState) {
        is NotesState.Loading -> {
            Text(text = "Loading...")
        }
        is NotesState.FinishLoading -> {
            Column {
                Text(text = "Finish loading")
                NoteList(notesVm)
            }
        }
        is NotesState.Error -> {
            Text(text = (notesState as NotesState.Error).message)
        }
        is NotesState.Start -> {
            Text(text = "Start")
        }
    }

}

@Composable
fun NoteList(vm: NotesViewModel) {
    val data: List<Note> = vm.notesFlowData.collectAsState(initial = listOf()).value

    if (data.isEmpty()) {
        Text(text = "Note list is empty")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(data.size) {index ->
                data[index].title?.let { Text(text = it) }
            }
        }
    }
}

@Composable
@Preview
fun NotesScreenPreview() {
    NotesScreen(modifier = Modifier)
}
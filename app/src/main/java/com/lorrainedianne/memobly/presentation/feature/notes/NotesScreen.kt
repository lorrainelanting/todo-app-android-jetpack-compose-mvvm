package com.lorrainedianne.memobly.presentation.feature.notes

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
    val data: List<Note> = notesVm.notesFlowData.collectAsState(initial = listOf()).value

    LaunchedEffect(Unit) {
        notesVm.onStart()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(data.size) {index ->
            data[index].title?.let { Text(text = it) }
        }
    }
}

@Composable
@Preview
fun NotesScreenPreview() {
    NotesScreen(modifier = Modifier)
}
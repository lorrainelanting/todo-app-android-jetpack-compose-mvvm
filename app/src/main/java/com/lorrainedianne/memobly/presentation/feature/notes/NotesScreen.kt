package com.lorrainedianne.memobly.presentation.feature.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.presentation.common.appBar.AppBarEventType
import com.lorrainedianne.memobly.presentation.common.appBar.AppBarViewModel
import com.lorrainedianne.memobly.presentation.feature.main.MainEventType
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel

/** This will be the Home Screen of the app.
 * This screen will show all the list of notes. **/
@Composable
fun NotesScreen(
    notesVm: NotesViewModel = hiltViewModel(),
    appBarViewModel: AppBarViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    modifier: Modifier,
    onClickFab: (() -> Unit) -> Unit
) {
    val notesState by notesVm.uiState.collectAsState()
    val topBarState by appBarViewModel.topBarState.collectAsState()

    LaunchedEffect(Unit) {
        notesVm.onEvent(NotesEventType.Start)

        onClickFab {
            appBarViewModel.onEvent(AppBarEventType.NoteItemTopBarShow)

//            if (topBarState == TopBarState.MainAppBarShow) {
                mainViewModel.onEvent(MainEventType.NavigateToNoteItem())
//            } else {
//                Log.d("Add Notes", topBarState.toString())
//            }
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
    val data: List<Note> = vm.notesFlowData.collectAsState(initial = listOf()).value

    if (data.isEmpty()) {
        Text(text = "Note list is empty", modifier = modifier)
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(data.size) { index ->
                data[index].title?.let { Text(text = it) }
            }
        }
    }
}
package com.lorrainedianne.memobly.presentation.feature.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.useCase.note.GetNotesUseCase
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val navManager: NavManager
) :
    ViewModel(), BaseViewModel<NotesEventType> {
    private val _notesFlow: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    var notesFlow: StateFlow<List<Note>> = _notesFlow
    private val _uiState: MutableStateFlow<NotesState> = MutableStateFlow(NotesState.Start)
    val uiState: StateFlow<NotesState> = _uiState

    private fun fetchData() {
        _uiState.value = NotesState.Loading

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    getNotesUseCase.invoke().collect {
                        _notesFlow.value = it

                        delay(3000)

                        withContext(Dispatchers.Main) {
                            onFetchSuccess()
                        }
                    }
                } catch (error: Exception) {
                    _uiState.value = NotesState.Error(error.message.toString())
                    onFetchFailed(error.message.toString())
                }
            }
        }
    }

    private fun onFetchSuccess() {
        _uiState.value = NotesState.FinishLoading
    }

    private fun onFetchFailed(message: String) {
        _uiState.value = NotesState.Error(message)
    }

    private fun onStart() {
        fetchData()

        viewModelScope.launch {
            navManager.lastPoppedRoute.collect { route ->
                route?.let {
                    onPopBackStack(it)
                }
            }
        }
    }

    private fun onPopBackStack(previousRoute: Route?) {
        Log.d("NAVIGATE_TO", "NotesVM.onPopBackStack $previousRoute")
        if (previousRoute == Route.NoteItem) {
            onPopNoteItem()
        }
    }

    private fun onPopNoteItem() {
        Log.d("NAVIGATE_TO", "NotesVM POP_TO_NOTE_ITEM")
        fetchData()
    }
    private fun onNavigateToNoteItem(id: Long) {
        navManager.navigateToNoteItem(id)
    }

    override fun onEvent(type: NotesEventType) {
        return when (type) {
            is NotesEventType.Start -> {
                onStart()
            }

            is NotesEventType.Error -> {

            }

            is NotesEventType.NavigateToNoteItem -> {
                onNavigateToNoteItem(type.id)
            }
        }
    }
}
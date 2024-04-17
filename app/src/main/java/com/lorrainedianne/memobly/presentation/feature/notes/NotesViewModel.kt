package com.lorrainedianne.memobly.presentation.feature.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.useCase.note.GetNotesUseCase
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase) :
    ViewModel(), BaseViewModel<NotesEventType> {

    var notesFlowData: Flow<List<Note>> = emptyFlow()
    private val _uiState: MutableStateFlow<NotesState> = MutableStateFlow(NotesState.Start)
    val uiState: StateFlow<NotesState> = _uiState

    private fun fetchData() {
        _uiState.value = NotesState.Loading

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    notesFlowData = getNotesUseCase.invoke()

                    delay(1000)
                    withContext(Dispatchers.Main) {
                        onFetchSuccess()
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
        _uiState.value = NotesState.Start
        fetchData()
    }

    override fun onEvent(type: NotesEventType) {
        return when (type) {
            is NotesEventType.Start -> {
                onStart()
            }
            is NotesEventType.Error -> {

            }
        }
    }
}
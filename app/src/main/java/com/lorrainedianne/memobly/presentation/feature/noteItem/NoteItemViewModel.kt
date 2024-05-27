package com.lorrainedianne.memobly.presentation.feature.noteItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.useCase.note.SaveNoteUseCase
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteItemViewModel @Inject constructor(private val saveNoteUseCase: SaveNoteUseCase) :
    ViewModel(), BaseViewModel<NoteItemEventType> {
    private val _uiState: MutableStateFlow<NoteItemState> = MutableStateFlow(NoteItemState.Start)
    val uiState: StateFlow<NoteItemState> = _uiState

    private fun onStart() {
        _uiState.value = NoteItemState.Start
    }

    private fun save(note: Note) {
        _uiState.value = NoteItemState.Saving

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    saveNoteUseCase.invoke(note)

                    delay(1000)
                    withContext(Dispatchers.Main) {
                        onSaveSuccess()
                    }
                } catch (error: Exception) {
                    onError(error.message.toString())
                }
            }
        }
    }

    private fun onSaveSuccess() {
        _uiState.value = NoteItemState.FinishSaving
    }

    private fun onError(message: String) {
        _uiState.value = NoteItemState.Error(message)
    }

    override fun onEvent(type: NoteItemEventType) {
        when (type) {
            is NoteItemEventType.Start -> {
                onStart()
            }

            is NoteItemEventType.Edit -> TODO()
            is NoteItemEventType.Error -> TODO()
            is NoteItemEventType.Save -> {
                save(type.note)
            }
        }
    }
}
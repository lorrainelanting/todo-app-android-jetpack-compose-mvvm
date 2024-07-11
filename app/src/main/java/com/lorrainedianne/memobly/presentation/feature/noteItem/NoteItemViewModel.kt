package com.lorrainedianne.memobly.presentation.feature.noteItem

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.useCase.note.EditNoteUseCase
import com.lorrainedianne.memobly.domain.useCase.note.GetNoteUseCase
import com.lorrainedianne.memobly.domain.useCase.note.SaveNoteUseCase
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteItemViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
    private val navManager: NavManager
) :
    ViewModel(), BaseViewModel<NoteItemEventType> {

    private var noteId: Long? = null

    init {
        val noteId = savedStateHandle.get<Long>("noteId")
        if (noteId != -1L) {
            this.noteId = noteId
        }
    }

    private val _uiState: MutableStateFlow<NoteItemState> = MutableStateFlow(NoteItemState.Start)
    val uiState: StateFlow<NoteItemState> = _uiState

    private val _noteFlow: MutableStateFlow<Note?> = MutableStateFlow(null)
    private val noteFlow: StateFlow<Note?> = _noteFlow

    private val _titleState: MutableState<String> = mutableStateOf("")
    val titleState: State<String> = _titleState

    private val _contentState: MutableState<String> = mutableStateOf("")
    val contentState: State<String> = _contentState

    private val _isDialogOpen: MutableState<Boolean> = mutableStateOf(false)
    val isDialogOpen: State<Boolean> = _isDialogOpen

    private fun onStart() {
        _uiState.value = NoteItemState.Start
        onViewCreated()
    }

    private fun onViewCreated() {
        _uiState.value = NoteItemState.Loading
        fetchData()

//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                delay(1000)
//            }
//
//            withContext(Dispatchers.Main) {
//                _uiState.value = NoteItemState.FinishLoading
//            }
//        }
    }

    private fun onTitleChanged(title: String) {
        _titleState.value = title
    }

    private fun onContentChanged(content: String) {
        _contentState.value = content
    }

    private fun onBackPressed() {
        save()
        Log.d("DATA_DEBUG", "NOTE_ITEM VM. After save()")
        Log.d("DATA_DEBUG", "NOTE_ITEM VM NoteItem: ${_noteFlow.value}")
    }

    private fun fetchData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (noteId != null) {
                        _noteFlow.value = getNoteUseCase.invoke(noteId!!)
                    }

                    withContext(Dispatchers.Main) {
                        _uiState.value = NoteItemState.FinishLoading

                        _titleState.value = noteFlow.value?.title ?: ""
                        _contentState.value = noteFlow.value?.note ?: ""
                    }

                } catch (error: Exception) {
                    onError(error.localizedMessage?.toString() ?: error.message.toString())
                }
            }
        }
    }

    /** When user clicks back button and there's nothing to save.
     * pop()
     * ***/
    private fun onNothingToSave() {
        navManager.pop()
    }

    private fun save() {
        _uiState.value = NoteItemState.Saving

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (_titleState.value == "" || _contentState.value == "") {
                        withContext(Dispatchers.Main) {
                            onNothingToSave()
                        }
                    } else {
                        val note =
                            Note.newInstance(
                                id = System.currentTimeMillis(),
                                title = _titleState.value,
                                note = _contentState.value,
                                type = "note",
                                isCompleted = false
                            )

                        if (noteId == noteFlow.value?.id && noteId != null) {
                            note.id = noteId as Long
                            editNoteUseCase.invoke(note)

                            Log.d("DATA_DEBUG", "NOTE_ITEM = AFTER editNoteUseCase")
                        } else {
                            saveNoteUseCase.invoke(note)

                            Log.d("DATA_DEBUG", "NOTE_ITEM = AFTER saveNoteUseCase")
                        }

                        delay(1000)
                        withContext(Dispatchers.Main) {
                            onSaveSuccess()
                        }
                    }

                } catch (error: Exception) {
                    _isDialogOpen.value = true
                    onError(error.message.toString())
                }
            }
        }
    }

    private fun onSaveSuccess() {
//        _uiState.value = NoteItemState.FinishSaving
        navManager.pop()
    }

    private fun onError(message: String) {
        _uiState.value = NoteItemState.Error(message)
    }

    private fun onConfirmDialogClicked() {
        _isDialogOpen.value = false
    }

    private fun onDismissDialog() {
        _isDialogOpen.value = false
    }

    override fun onEvent(type: NoteItemEventType) {
        when (type) {
            is NoteItemEventType.Start -> onStart()
            is NoteItemEventType.Edit -> TODO()
            is NoteItemEventType.Error -> TODO()
            is NoteItemEventType.BackPressed -> onBackPressed()
            is NoteItemEventType.ContentChanged -> onContentChanged(type.content)
            is NoteItemEventType.TitleChanged -> onTitleChanged(type.title)
            is NoteItemEventType.ConfirmDialog -> onConfirmDialogClicked()
            is NoteItemEventType.DismissDialog -> onDismissDialog()
        }
    }
}
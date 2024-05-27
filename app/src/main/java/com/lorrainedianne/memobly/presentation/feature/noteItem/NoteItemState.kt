package com.lorrainedianne.memobly.presentation.feature.noteItem

sealed class NoteItemState {
    data object Start : NoteItemState()
    data object Loading : NoteItemState()
    data object FinishLoading : NoteItemState()
    data class Error(val message: String) : NoteItemState()
    data object Saving : NoteItemState()
    data object FinishSaving : NoteItemState()
}
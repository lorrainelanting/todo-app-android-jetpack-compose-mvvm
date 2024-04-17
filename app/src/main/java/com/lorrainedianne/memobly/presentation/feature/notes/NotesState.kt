package com.lorrainedianne.memobly.presentation.feature.notes

sealed class NotesState {
    data object Start: NotesState()
    data object Loading: NotesState()
    data object FinishLoading: NotesState()
    data class Error(val message: String): NotesState()
}
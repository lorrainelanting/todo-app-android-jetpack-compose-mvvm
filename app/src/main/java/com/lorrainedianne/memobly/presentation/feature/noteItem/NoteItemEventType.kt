package com.lorrainedianne.memobly.presentation.feature.noteItem

import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent

sealed class NoteItemEventType : BaseEvent() {
    data object Start : NoteItemEventType()
    data class Error(val message: String) : NoteItemEventType()
    data class Edit(val note: Note) : NoteItemEventType()
    data object BackPressed: NoteItemEventType()
    data object DismissDialog: NoteItemEventType()
    data object ConfirmDialog: NoteItemEventType()
    data class TitleChanged(val title: String) : NoteItemEventType()
    data class ContentChanged(val content: String) : NoteItemEventType()
}
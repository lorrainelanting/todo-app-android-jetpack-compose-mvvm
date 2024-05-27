package com.lorrainedianne.memobly.presentation.feature.noteItem

import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent

sealed class NoteItemEventType : BaseEvent() {
    data object Start : NoteItemEventType()
    data class Error(val message: String) : NoteItemEventType()
    data class Save(val note: Note) : NoteItemEventType()
    data class Edit(val note: Note) : NoteItemEventType()
}
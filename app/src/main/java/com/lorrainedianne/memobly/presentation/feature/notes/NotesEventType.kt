package com.lorrainedianne.memobly.presentation.feature.notes

import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent

sealed class NotesEventType: BaseEvent() {
    data object Start: NotesEventType()
    data class Error(val message: String) : NotesEventType()
}
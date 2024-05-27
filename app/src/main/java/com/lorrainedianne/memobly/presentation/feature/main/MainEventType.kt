package com.lorrainedianne.memobly.presentation.feature.main

import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent

sealed class MainEventType: BaseEvent() {
    data object Start: MainEventType()
    data object PopBackStack: MainEventType()
    data object NavigateToNotes: MainEventType()
    data object NavigateToCalendar: MainEventType()
    data object NavigateToProfile: MainEventType()
    data class NavigateToNoteItem(val id: String? = null): MainEventType()
    data class NavigateToBottomNav(val route: String): MainEventType()
}
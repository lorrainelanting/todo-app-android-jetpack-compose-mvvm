package com.lorrainedianne.memobly.presentation.feature.main

import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent
import com.lorrainedianne.memobly.presentation.route.Route

sealed class MainEventType: BaseEvent() {
    data object Start: MainEventType()
    data class PopBackStack(val prevRoute: Route?): MainEventType()
    data object NavigateToNotes: MainEventType()
    data object NavigateToCalendar: MainEventType()
    data object NavigateToProfile: MainEventType()
    data class NavigateToNoteItem(val id: Long): MainEventType()
    data class NavigateToBottomNav(val route: String): MainEventType()
}
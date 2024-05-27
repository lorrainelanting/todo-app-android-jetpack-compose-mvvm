package com.lorrainedianne.memobly.presentation.feature.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navManager: NavManager
) : ViewModel(), BaseViewModel<MainEventType> {
    init {
        Log.d("NAVIGATE_TO", "MainVM init")
    }

    val currentRoute: StateFlow<Route?> = navManager.currentRouteAsFlow

    private fun onStart() {
        Log.d("onStart MainVM", "1")
        viewModelScope.launch {
            navManager.lastPoppedRoute.collect { route ->
                route?.let {
                    onPopBackStack(it)
                }
            }
        }
    }

    private fun onPopBackStack(previousRoute: Route) {
        Log.d("NAVIGATE_TO", "MainVM.onPopBackStack $previousRoute")
        // don't confuse with currentRoute
        navManager.currentRoute()?.let {
        }
    }

    private fun onNavigateToBottomNav(route: String) {
        navManager.navigateBottomNavTo(route)
    }

    private fun onNavigateToNotesScreen() {
        navManager.navigateToNotes()
        Log.d("NAVIGATE_TO", "onNavigateToNotesScreen")
    }

    private fun onNavigateToCalendarScreen() {
        navManager.navigateToCalendar()
    }

    private fun onNavigateToProfile() {
        navManager.navigateToProfile()
    }

    private fun onNavigateToNoteItemScreen(id: String? = null) {
        navManager.navigateToNoteItem()
        Log.d("NAVIGATE_TO", "onNavigateToNoteItemScreen")
    }

    override fun onEvent(type: MainEventType) {
        Log.d("NAVIGATE_TO", "MainVM.onEvent $type")
        when (type) {
            is MainEventType.NavigateToNotes -> {
                onNavigateToNotesScreen()
            }

            is MainEventType.NavigateToCalendar -> {
                onNavigateToCalendarScreen()
            }

            is MainEventType.NavigateToNoteItem -> {
                onNavigateToNoteItemScreen(type.id)
            }

            is MainEventType.NavigateToProfile -> {
                onNavigateToProfile()
            }

            is MainEventType.NavigateToBottomNav -> {
                onNavigateToBottomNav(type.route)
            }

            is MainEventType.PopBackStack -> TODO()

            is MainEventType.Start -> {
                onStart()
            }
        }
    }
}
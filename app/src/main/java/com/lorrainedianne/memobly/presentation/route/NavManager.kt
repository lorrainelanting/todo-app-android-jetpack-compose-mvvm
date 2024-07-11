package com.lorrainedianne.memobly.presentation.route

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class NavManager {

    private var navController: NavController? = null

    private val _lastPoppedRoute: MutableStateFlow<Route?> = MutableStateFlow(null)
    val lastPoppedRoute: StateFlow<Route?> = _lastPoppedRoute
    private var lastDestination: String? = null
    private val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )
    private val _currentRoute: MutableStateFlow<Route?> = MutableStateFlow(null)
    val currentRouteAsFlow: StateFlow<Route?> = _currentRoute

    init {
        Log.d("NAVIGATE_TO", "NavManager init")
    }

    fun setController(navController: NavController) {
        Log.d("NAVIGATE_TO", "NavManager.setController")
        this.navController = navController

        scope.launch {
            navController.currentBackStackEntryFlow.collect {
                Log.d(
                    "NAVIGATE_TO",
                    "NavMgr.currentBackStackEntryFlow.collect ${it.destination.route}"
                )
                lastDestination?.let { lastDestination ->
                    onPopBackStack(lastDestination)
                }
                lastDestination = it.destination.route

                onPush(it.destination.route)
            }
        }
    }

    private suspend fun onPush(route: String?) {
        Log.d("NAVIGATE_TO", "NavMgr.onPush $route")

        when (route) {
            Route.Notes.path -> {
                _currentRoute.emit(Route.Notes)
            }

            Route.Calendar.path -> {
                _currentRoute.emit(Route.Calendar)
            }

            Route.Profile.path -> {
                _currentRoute.emit(Route.Profile)
            }

            Route.NoteItem.path -> {
                _currentRoute.emit(Route.NoteItem)
            }
        }
    }

    fun navigateBottomNavTo(route: String) {
        navController?.navigate(route) {
            Log.d("NAVIGATE_TO", "navigateBottomNavTo $route")
            navController?.graph?.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true

                    Log.d("NAVIGATE_TO", "popUpTo $route")
                }
            }

            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToNoteItem(id: Long) {
        navController?.navigate("${Route.NoteItem.path}/$id")
    }

    fun navigateToNotes() {
        navController?.navigate(Route.Notes.path)
    }

    fun navigateToCalendar() {
        navController?.navigate(Route.Calendar.path)
    }

    fun navigateToProfile() {
        navController?.navigate(Route.Profile.path)
    }

    private suspend fun onPopBackStack(route: String) {
        Log.d("NAVIGATE_TO", "NavMgr.onPopBackStack $route")

        if (route == "${Route.NoteItem.path}/{noteId}") {
            _lastPoppedRoute.emit(Route.NoteItem)
        }

//        when (route) {
//            Route.Notes.path -> {
//                _lastPoppedRoute.emit(Route.Notes)
//            }
//
//            Route.Calendar.path -> {
//                _lastPoppedRoute.emit(Route.Calendar)
//            }
//
//            Route.Profile.path -> {
//                _lastPoppedRoute.emit(Route.Profile)
//            }
//
//            Route.NoteItem.path -> {
//                Log.d("NAVIGATE_TO", "Last popped route = ${Route.NoteItem.path}")
//            }
//        }

    }

    fun pop(): Boolean {
        return navController?.popBackStack() ?: false
    }

    fun currentDestination(): NavDestination? {
        return navController?.currentDestination
    }

    fun currentRoute(): Route? {
        navController?.currentDestination?.route?.let { route ->
            when (route) {
                Route.Notes.path -> {
                    return Route.Notes
                }

                Route.Calendar.path -> {
                    return Route.Calendar
                }

                Route.Profile.path -> {
                    return Route.Profile
                }

                Route.NoteItem.path -> {
                    return Route.NoteItem
                }

                else -> {
                    return null
                }// THROW
            }
        }
        return null

    }

    fun clear() {
        navController = null
        scope.cancel()
    }
}
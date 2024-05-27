package com.lorrainedianne.memobly.presentation.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lorrainedianne.memobly.presentation.feature.calendar.CalendarScreen
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel
import com.lorrainedianne.memobly.presentation.feature.noteItem.NoteItemScreen
import com.lorrainedianne.memobly.presentation.feature.notes.NotesScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    mainVM: MainViewModel,
    modifier: Modifier,
    setFabOnClick: ((() -> Unit)?) -> Unit,
    setOnBackPressed: ((() -> Unit)?) -> Unit
) {

    NavHost(navController = navController, startDestination = Route.Notes.path) {
        composable(Route.Notes.path) {
            Log.d("NAVIGATE_TO", "NavHost.NotesScreen")
            NotesScreen(
                mainViewModel = mainVM,
                modifier = modifier,
                onClickFab = setFabOnClick
            )
        }
        composable(Route.Calendar.path) {
            CalendarScreen(onClickFab = setFabOnClick)
        }
        composable(Route.Profile.path) {
//            ProfileScreen()
        }
        composable(Route.NoteItem.path) {
            Log.d("NAVIGATE_TO", "NavHost.NoteItemScreen")
            NoteItemScreen(
                modifier = modifier,
                mainViewModel = mainVM,
                onBackPressed = setOnBackPressed
            )
        }
    }
}
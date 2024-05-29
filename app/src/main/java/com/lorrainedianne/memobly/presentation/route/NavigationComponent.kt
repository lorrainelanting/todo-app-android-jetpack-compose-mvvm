package com.lorrainedianne.memobly.presentation.route

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
    setFabOnClick: ((() -> Unit)?) -> Unit
) {

    NavHost(navController = navController, startDestination = Route.Notes.path) {
        composable(Route.Notes.path) {
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
            NoteItemScreen(
                modifier = modifier,
                mainViewModel = mainVM
            )
        }
    }
}
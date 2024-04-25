package com.lorrainedianne.memobly.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lorrainedianne.memobly.presentation.feature.notes.NotesScreen

@Composable
fun NavigationScreen(navHostController: NavHostController, modifier: Modifier) {
    NavHost(navController = navHostController, startDestination = NavItem.Notes.path) {
        composable(NavItem.Notes.path) { NotesScreen(modifier = modifier) }
        composable(NavItem.Calendar.path) {
//            CalendarScreen()
        }
        composable(NavItem.Profile.path) {
//            ProfileScreen()
        }
        composable(NavItem.More.path) {
//            MoreScreen()
        }
    }
}
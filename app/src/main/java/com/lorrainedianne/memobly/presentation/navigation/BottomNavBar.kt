package com.lorrainedianne.memobly.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navHostController: NavHostController) {
    val navItems = listOf(NavItem.Notes, NavItem.Calendar, NavItem.Profile, NavItem.More)
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.path == backStackEntry.value?.destination?.route,
                onClick = {
                    selectedItem = index
                    navHostController.navigate(item.path) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title)  },
                label = { Text(text = item.title) })
        }
    }
}
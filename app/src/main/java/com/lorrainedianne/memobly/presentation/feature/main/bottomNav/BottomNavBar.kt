package com.lorrainedianne.memobly.presentation.feature.main.bottomNav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.lorrainedianne.memobly.presentation.feature.main.MainEventType
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager

@Composable
fun BottomNavBar(
    navManager: NavManager,
    mainViewModel: MainViewModel
) {
    val navItems = listOf(NavItem.Notes, NavItem.Calendar, NavItem.Profile)
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val currDestination = navManager.currentDestination()

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = item.path.value == (currDestination?.route ?: ""),
                onClick = {
                    selectedItem = index
                    mainViewModel.onEvent(MainEventType.NavigateToBottomNav(item.path.value))
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true
            )
        }
    }
}
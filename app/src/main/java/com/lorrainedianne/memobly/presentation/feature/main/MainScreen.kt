package com.lorrainedianne.memobly.presentation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.lorrainedianne.memobly.presentation.common.appBar.AppBarViewModel
import com.lorrainedianne.memobly.presentation.feature.main.bottomNav.BottomNavBar
import com.lorrainedianne.memobly.presentation.feature.main.topBar.MainTopAppBar
import com.lorrainedianne.memobly.presentation.feature.noteItem.NoteItemTopAppBar
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.route.NavigationComponent
import com.lorrainedianne.memobly.presentation.route.Route

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    appBarVm: AppBarViewModel = hiltViewModel(),
    navController: NavHostController,
    navManager: NavManager
) {
    val currentRoute by mainViewModel.currentRoute.collectAsState()
    val (fabOnClick, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null) }

    Scaffold(
        topBar = {
            if (appBarVm.isNoteItemTopBarVisible()) {
                NoteItemTopAppBar()
            } else {
                MainTopAppBar()
            }
        },
        bottomBar = {
            if (appBarVm.isBottomBarVisible()) {
                BottomAppBar(content = {
                    BottomNavBar(
                        navManager = navManager,
                        mainViewModel = mainViewModel
                    )
                })
            }
        },
        floatingActionButton = {
            when (currentRoute) {
                Route.Notes -> {
                    FloatingActionButton(
                        onClick = {
                            fabOnClick?.invoke()
                        }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }

                Route.Calendar -> {
                    FloatingActionButton(
                        onClick = {
                            fabOnClick?.invoke()
                        }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                }

                else -> {
                }
            }
        }
    ) { innerPadding ->
        NavigationComponent(
            navController = navController,
            mainVM = mainViewModel,
            modifier = Modifier.padding(innerPadding),
            setFabOnClick = setFabOnClick
        )
    }
}
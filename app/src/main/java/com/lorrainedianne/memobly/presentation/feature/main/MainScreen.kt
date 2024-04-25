package com.lorrainedianne.memobly.presentation.feature.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lorrainedianne.memobly.presentation.navigation.BottomNavBar
import com.lorrainedianne.memobly.presentation.navigation.NavigationScreen

@Composable
fun MainScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {},
        bottomBar = {
            BottomAppBar(content = {
                BottomNavBar(navHostController = navHostController)
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add note.")
            }
        }
    ) { innerPadding ->
        NavigationScreen(
            navHostController = navHostController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
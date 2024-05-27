package com.lorrainedianne.memobly.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.lorrainedianne.memobly.presentation.feature.main.MainEventType
import com.lorrainedianne.memobly.presentation.feature.main.MainScreen
import com.lorrainedianne.memobly.presentation.feature.main.MainViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.theme.MemoblyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navManager: NavManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoblyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    DisposableEffect(key1 = navController) {
                        navManager.setController(navController)
                        onDispose {
                            navManager.clear()
                        }
                    }

                    val mainViewModel: MainViewModel = hiltViewModel()
                    mainViewModel.onEvent(MainEventType.Start)
                    MainScreen(
                        navController = navController,
                        navManager = navManager,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}
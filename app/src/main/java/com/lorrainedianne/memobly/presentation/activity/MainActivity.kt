package com.lorrainedianne.memobly.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lorrainedianne.memobly.presentation.feature.notes.NotesScreen
import com.lorrainedianne.memobly.presentation.theme.MemoblyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoblyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    Scaffold(bottomBar = {}, content = { innerPadding ->
        NotesScreen(modifier = Modifier.padding(innerPadding))
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoblyTheme {
        AppContent()
    }
}
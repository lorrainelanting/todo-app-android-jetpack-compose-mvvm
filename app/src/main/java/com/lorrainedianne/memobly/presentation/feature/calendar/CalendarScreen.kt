package com.lorrainedianne.memobly.presentation.feature.calendar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CalendarScreen(onClickFab: (() -> Unit) -> Unit) {
    val context: Context = LocalContext.current

    LaunchedEffect(Unit) {
        onClickFab {
            Toast.makeText(context, "Add Calendar", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Calendar", fontWeight = FontWeight.Bold, fontSize = 50.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun CalendarScreenPreview() {
    CalendarScreen(onClickFab = {})
}
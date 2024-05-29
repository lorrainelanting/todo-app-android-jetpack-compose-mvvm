package com.lorrainedianne.memobly.presentation.feature.main.topBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.lorrainedianne.memobly.R
import com.lorrainedianne.memobly.presentation.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Color.White,
        ),
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}
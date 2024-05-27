package com.lorrainedianne.memobly.presentation.feature.main.bottomNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home


sealed class NavItem(path: String) {
    object Notes :
        Item(path = NavPath.NOTES, title = NavTitle.NOTES, icon = Icons.Default.Home)

    object Calendar : Item(
        path = NavPath.CALENDAR,
        title = NavTitle.CALENDAR,
        icon = Icons.Default.DateRange
    )

    object Profile : Item(
        path = NavPath.PROFILE,
        title = NavTitle.PROFILE,
        icon = Icons.Default.AccountCircle
    )
}
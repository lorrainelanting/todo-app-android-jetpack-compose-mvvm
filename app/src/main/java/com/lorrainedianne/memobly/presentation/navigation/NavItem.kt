package com.lorrainedianne.memobly.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu


sealed class NavItem(path: String) {
    object Notes :
        Item(path = NavPath.NOTES.toString(), title = NavTitle.NOTES, icon = Icons.Default.Home)

    object Calendar : Item(
        path = NavPath.CALENDAR.toString(),
        title = NavTitle.CALENDAR,
        icon = Icons.Default.DateRange
    )

    object Profile : Item(
        path = NavPath.PROFILE.toString(),
        title = NavTitle.PROFILE,
        icon = Icons.Default.AccountCircle
    )

    object More :
        Item(path = NavPath.MORE.toString(), title = NavTitle.MORE, icon = Icons.Default.Menu)

}
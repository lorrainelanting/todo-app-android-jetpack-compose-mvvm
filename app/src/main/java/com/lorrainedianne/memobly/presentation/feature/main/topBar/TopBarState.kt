package com.lorrainedianne.memobly.presentation.feature.main.topBar

sealed class TopBarState {
    data object NoteItemAppBarShow: TopBarState()
    data object NoteItemAppBarHidden: TopBarState()
    data object MainAppBarShow: TopBarState()
    data object MainAppBarHidden: TopBarState()
}
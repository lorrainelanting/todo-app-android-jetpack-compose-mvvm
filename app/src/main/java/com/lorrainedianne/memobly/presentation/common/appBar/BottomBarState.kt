package com.lorrainedianne.memobly.presentation.common.appBar

sealed class BottomBarState {
    data object NoteItemAppBarShow: BottomBarState()
    data object NoteItemAppBarHidden: BottomBarState()
    data object MainAppBarShow: BottomBarState()
    data object MainAppBarHidden: BottomBarState()
}
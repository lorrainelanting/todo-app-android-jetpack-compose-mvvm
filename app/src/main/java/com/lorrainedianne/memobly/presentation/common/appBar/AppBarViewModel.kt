package com.lorrainedianne.memobly.presentation.common.appBar

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import com.lorrainedianne.memobly.presentation.feature.main.topBar.TopBarState
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(private val navManager: NavManager) : ViewModel(),
    BaseViewModel<AppBarEventType> {
    private val _topBarState: MutableStateFlow<TopBarState> =
        MutableStateFlow(TopBarState.MainAppBarShow)
    private val _bottomBarState: MutableStateFlow<BottomBarState> =
        MutableStateFlow(BottomBarState.MainAppBarShow)

    val topBarState: StateFlow<TopBarState> = _topBarState
    val bottomBarState: StateFlow<BottomBarState> = _bottomBarState

    private fun onShowNoteItemTopBar() {
        _topBarState.value = TopBarState.NoteItemAppBarShow
    }

    private fun onHiddenNoteItemBottomBar() {
        _bottomBarState.value = BottomBarState.MainAppBarShow
    }

    override fun onEvent(type: AppBarEventType) {
        return when (type) {
            is AppBarEventType.NoteItemTopBarShow -> {
                onShowNoteItemTopBar()
            }

            is AppBarEventType.NoteItemBottomBarShow -> {
                onHiddenNoteItemBottomBar()
            }
        }
    }

    // COMPUTED VARIABLE/STATE IS PREFERRED OVER MUTABLE STATE
    fun isBottomBarVisible(): Boolean {
        val bottomBarVisibleRoutes =
            arrayListOf(Route.Notes.path, Route.Profile.path, Route.Calendar.path)
        Log.d("NAVIGATE_TO", navManager.currentRoute()?.path.toString())
        Log.d(
            "NAVIGATE_TO",
            bottomBarVisibleRoutes.contains(navManager.currentRoute()?.path).toString()
        )

        return bottomBarVisibleRoutes.contains(navManager.currentRoute()?.path)
    }

    fun isNoteItemTopBarVisible(): Boolean {
        val noteItemRoute = Route.NoteItem.path

        return navManager.currentRoute()?.path == noteItemRoute
    }
}
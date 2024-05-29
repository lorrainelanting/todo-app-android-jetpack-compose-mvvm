package com.lorrainedianne.memobly.presentation.common.appBar

import androidx.lifecycle.ViewModel
import com.lorrainedianne.memobly.presentation.feature.base.BaseViewModel
import com.lorrainedianne.memobly.presentation.route.NavManager
import com.lorrainedianne.memobly.presentation.route.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppBarViewModel @Inject constructor(private val navManager: NavManager) : ViewModel(),
    BaseViewModel<AppBarEventType> {

    // COMPUTED VARIABLE/STATE IS PREFERRED OVER MUTABLE STATE
    fun isBottomBarVisible(): Boolean {
        val bottomBarVisibleRoutes =
            arrayListOf(Route.Notes.path, Route.Profile.path, Route.Calendar.path)

        return bottomBarVisibleRoutes.contains(navManager.currentRoute()?.path)
    }

    fun isNoteItemTopBarVisible(): Boolean {
        val noteItemRoute = Route.NoteItem.path

        return navManager.currentRoute()?.path == Route.NoteItem.path
    }

    override fun onEvent(type: AppBarEventType) {
        TODO("Not yet implemented")
    }
}
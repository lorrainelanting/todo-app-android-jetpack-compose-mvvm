package com.lorrainedianne.memobly.presentation.common.appBar

import com.lorrainedianne.memobly.presentation.feature.base.BaseEvent

sealed class AppBarEventType: BaseEvent() {
//    data object TopBarChanged: AppBarEventType()
//    data object BottomBarChanged: AppBarEventType()
    data object NoteItemTopBarShow: AppBarEventType()
    data object NoteItemBottomBarShow: AppBarEventType()
}
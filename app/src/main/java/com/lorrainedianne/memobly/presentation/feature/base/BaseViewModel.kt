package com.lorrainedianne.memobly.presentation.feature.base

interface BaseViewModel<E:BaseEvent> {
    /** public viewModel event. **/
    fun onEvent(type: E)
}
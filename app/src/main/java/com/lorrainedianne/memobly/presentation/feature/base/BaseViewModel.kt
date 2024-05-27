package com.lorrainedianne.memobly.presentation.feature.base

interface BaseViewModel<E:BaseEvent> {
    /** Public viewModel event.
     * The view will access the events from ViewModel by calling
     * the [onEvent] method passing the type of event. **/
    fun onEvent(type: E)
}
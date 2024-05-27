package com.lorrainedianne.memobly.presentation.route

/** Routes manages by Main scaffold. **/
sealed class Route(val path: String) {
    data object Notes: Route(path = "notes")
    data object Calendar: Route(path = "calendar")
    data object Profile: Route(path = "profile")
    data object NoteItem: Route(path = "noteItem")
}
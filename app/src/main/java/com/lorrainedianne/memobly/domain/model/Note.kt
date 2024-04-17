package com.lorrainedianne.memobly.domain.model

import androidx.room.Entity

@Entity(tableName = "note_table")
data class Note(
    val title: String?,
    val note: String?,
    val isCompleted: Boolean = false,
    val type: String // checklist or text
): Base()

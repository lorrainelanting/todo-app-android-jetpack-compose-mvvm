package com.lorrainedianne.memobly.domain.model

import androidx.room.Entity

@Entity(tableName = "checklist_item_table")
data class ChecklistItem(
    val noteId: String?, // TODO: FK note.id
    val item: String?,
    val isCompleted: Boolean = false
): Base()

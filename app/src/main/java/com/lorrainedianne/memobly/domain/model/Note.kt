package com.lorrainedianne.memobly.domain.model

import androidx.room.Entity

@Entity(tableName = "note_table")
class Note : Base() {
    var title: String? = ""
    var note: String? = ""
    var isCompleted: Boolean = false
    var type: String = "" // checklist or text

    companion object {
        fun newInstance(
            id: Long,
            title: String?,
            note: String?,
            isCompleted: Boolean,
            type: String
        ): Note {
            val noteEntity = Note()
            noteEntity.id = id
            noteEntity.title = title
            noteEntity.note = note
            noteEntity.isCompleted = isCompleted
            noteEntity.type = type
            return noteEntity
        }
    }
}

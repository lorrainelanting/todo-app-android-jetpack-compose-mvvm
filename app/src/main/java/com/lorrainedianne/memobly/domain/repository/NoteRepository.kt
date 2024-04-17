package com.lorrainedianne.memobly.domain.repository

import com.lorrainedianne.memobly.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: String): Note
    suspend fun edit(note: Note)
    suspend fun save(note: Note)
    suspend fun permanentDelete(id: String)
}
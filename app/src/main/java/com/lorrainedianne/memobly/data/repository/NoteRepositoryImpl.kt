package com.lorrainedianne.memobly.data.repository

import android.util.Log
import com.lorrainedianne.memobly.data.dataSource.db.dao.NoteDao
import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(private val datasource: NoteDao) : NoteRepository {

    @Throws
    override suspend fun getAllNotes(): Flow<List<Note>> {
        try {
            return datasource.getNotes()
        } catch (error: Exception) {
            Log.d("GET_NOTES_ERROR", error.message.toString())
            throw Exception("Get notes DAO error.")
        }
    }

    @Throws
    override suspend fun getOneNote(id: Long): Note {
        try {
            return datasource.getNote(id)
        } catch (error: Exception) {
            Log.d("GET_NOTE_ERROR", error.message.toString())
            throw Exception("Get note DAO error.")
        }
    }

    @Throws
    override suspend fun edit(note: Note) {
        try {
            Log.d("DATA_DEBUG", "REPO EDIT NOTE = $note")
            return datasource.update(note)
        } catch (error: Exception) {
            Log.d("EDIT_NOTE_ERROR", error.message.toString())
            throw Exception("Edit note DAO error.")
        }
    }

    @Throws
    override suspend fun save(note: Note) {
        try {
            return datasource.insert(note)
        } catch (error: Exception) {
            Log.d("INSERT_NOTE_ERROR", error.message.toString())
            throw Exception("Insert note DAO error.")
        }
    }

    @Throws
    override suspend fun permanentDelete(id: Long) {
        try {
            return datasource.delete(id)
        } catch (error: Exception) {
            Log.d("PERMANENT_DELETE_NOTE_ERROR", error.message.toString())
            throw Exception("Permanent delete note DAO error.")
        }
    }
}
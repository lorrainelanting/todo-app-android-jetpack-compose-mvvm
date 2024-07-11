package com.lorrainedianne.memobly.data.dataSource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lorrainedianne.memobly.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY createdAtTimeStamp DESC")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE id = :id LIMIT 1")
    fun getNote(id: Long): Note

    @Update
    fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("DELETE FROM note_table WHERE id = :id")
    fun delete(id: Long)

}
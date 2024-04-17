package com.lorrainedianne.memobly.data.dataSource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lorrainedianne.memobly.data.dataSource.db.dao.ChecklistItemDao
import com.lorrainedianne.memobly.data.dataSource.db.dao.NoteDao
import com.lorrainedianne.memobly.domain.model.ChecklistItem
import com.lorrainedianne.memobly.domain.model.Note

@Database(
    entities = [
        Note::class,
        ChecklistItem::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    abstract fun checklistItemDao(): ChecklistItemDao

    companion object {
        @Volatile
        var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(
            context: Context
        ): AppRoomDatabase {
            return Room.databaseBuilder(
                context,
                AppRoomDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
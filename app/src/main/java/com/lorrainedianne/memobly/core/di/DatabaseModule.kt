package com.lorrainedianne.memobly.core.di

import android.content.Context
import com.lorrainedianne.memobly.data.dataSource.db.AppRoomDatabase
import com.lorrainedianne.memobly.data.dataSource.db.dao.ChecklistItemDao
import com.lorrainedianne.memobly.data.dataSource.db.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return AppRoomDatabase.getInstance(context)
    }

    @Provides
    fun provideNoteDao(appRoomDatabase: AppRoomDatabase): NoteDao {
        return appRoomDatabase.noteDao()
    }

    @Provides
    fun provideChecklistItemDao(appRoomDatabase: AppRoomDatabase): ChecklistItemDao {
        return appRoomDatabase.checklistItemDao()
    }
}
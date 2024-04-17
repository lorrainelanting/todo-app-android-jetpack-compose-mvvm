package com.lorrainedianne.memobly.core.di

import com.lorrainedianne.memobly.data.repository.NoteRepositoryImpl
import com.lorrainedianne.memobly.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun providesNoteRepository(repositoryImpl: NoteRepositoryImpl): NoteRepository
}
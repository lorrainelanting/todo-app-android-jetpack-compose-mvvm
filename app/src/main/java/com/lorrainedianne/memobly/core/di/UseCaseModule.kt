package com.lorrainedianne.memobly.core.di

import com.lorrainedianne.memobly.domain.repository.NoteRepository
import com.lorrainedianne.memobly.domain.useCase.note.EditNoteUseCase
import com.lorrainedianne.memobly.domain.useCase.note.GetNoteUseCase
import com.lorrainedianne.memobly.domain.useCase.note.GetNotesUseCase
import com.lorrainedianne.memobly.domain.useCase.note.PermanentDeleteNoteUseCase
import com.lorrainedianne.memobly.domain.useCase.note.SaveNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    fun provideGetNoteUseCase(repository: NoteRepository): GetNoteUseCase {
        return GetNoteUseCase(repository)
    }

    @Provides
    fun provideEditNoteUseCase(repository: NoteRepository): EditNoteUseCase {
        return EditNoteUseCase(repository)
    }

    @Provides
    fun provideSaveNoteUseCase(repository: NoteRepository): SaveNoteUseCase {
        return SaveNoteUseCase(repository)
    }

    @Provides
    fun providePermanentDeleteNoteUseCase(repository: NoteRepository): PermanentDeleteNoteUseCase {
        return PermanentDeleteNoteUseCase(repository)
    }
}
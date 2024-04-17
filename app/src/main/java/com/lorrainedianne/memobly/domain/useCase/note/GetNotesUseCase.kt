package com.lorrainedianne.memobly.domain.useCase.note

import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.repository.NoteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}
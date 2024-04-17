package com.lorrainedianne.memobly.domain.useCase.note

import com.lorrainedianne.memobly.domain.model.Note
import com.lorrainedianne.memobly.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: String): Note {
        return repository.getNote(id)
    }
}
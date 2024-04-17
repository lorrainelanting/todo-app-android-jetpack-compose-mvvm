package com.lorrainedianne.memobly.domain.useCase.note

import com.lorrainedianne.memobly.domain.repository.NoteRepository
import javax.inject.Inject

class PermanentDeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(id: String) {
        return repository.permanentDelete(id)
    }
}
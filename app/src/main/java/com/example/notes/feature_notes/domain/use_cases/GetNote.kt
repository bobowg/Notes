package com.example.notes.feature_notes.domain.use_cases

import com.example.notes.feature_notes.domain.model.Note
import com.example.notes.feature_notes.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}
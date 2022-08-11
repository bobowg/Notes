package com.example.notes.feature_notes.domain.use_cases

import com.example.notes.feature_notes.domain.model.InvalidNoteException
import com.example.notes.feature_notes.domain.model.Note
import com.example.notes.feature_notes.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("标题不能为空!")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("内容不能为空!")
        }
        repository.insertNote(note)
    }
}

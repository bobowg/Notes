package com.example.notes.feature_notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.feature_notes.domain.model.Note
import com.example.notes.feature_notes.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeleteNote: Note? = null

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {

                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }
}
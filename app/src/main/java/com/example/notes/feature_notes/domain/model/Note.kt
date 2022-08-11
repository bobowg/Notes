package com.example.notes.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notes.ui.theme.*

@Entity
data class Note(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
) {
    companion object {
        val noteColor = listOf(
            RedOrange, LightGreen, Violet, BabyBule, RedPink
        )
    }
}
class InvalidNoteException(message:String):Exception(message)
package com.example.notes.feature_notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.feature_notes.data.data_source.NoteDatabase
import com.example.notes.feature_notes.data.repository.NoteRepositoryImpl
import com.example.notes.feature_notes.domain.repository.NoteRepository
import com.example.notes.feature_notes.domain.use_cases.AddNote
import com.example.notes.feature_notes.domain.use_cases.DeleteNote
import com.example.notes.feature_notes.domain.use_cases.GetNotes
import com.example.notes.feature_notes.domain.use_cases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository)
        )
    }
}
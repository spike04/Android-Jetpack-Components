package com.rubin.jetpackcomponents.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>

    init {
        val noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB!!.noteDao()

        allNotes = noteDao.allNotes
    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    companion object {
        private class InsertAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

            override fun doInBackground(vararg notes: Note): Void? {
                mNoteDao.insert(notes[0])
                return null
            }
        }

        private class UpdateAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

            override fun doInBackground(vararg notes: Note): Void? {
                mNoteDao.update(notes[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

            override fun doInBackground(vararg notes: Note): Void? {
                mNoteDao.delete(notes[0])
                return null
            }
        }
    }
}
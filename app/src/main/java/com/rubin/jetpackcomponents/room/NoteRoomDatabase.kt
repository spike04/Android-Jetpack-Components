package com.rubin.jetpackcomponents.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var noteRoomInstance: NoteRoomDatabase? = null

        internal fun getDatabase(context: Context): NoteRoomDatabase? {
            if (noteRoomInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (noteRoomInstance == null) {
                        noteRoomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java, "note.db"
                        ).build()
                    }
                }
            }
            return noteRoomInstance
        }
    }
}
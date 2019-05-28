package com.rubin.jetpackcomponents.room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_room_main.*
import kotlinx.android.synthetic.main.content_room_main.*
import java.util.*

class RoomMainActivity : AppCompatActivity(), NoteListAdapter.OnDeleteClickListener {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        val noteListAdapter = NoteListAdapter(this@RoomMainActivity, this@RoomMainActivity)
        recyclerView.adapter = noteListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@RoomMainActivity)

        noteViewModel = ViewModelProviders.of(this@RoomMainActivity).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this@RoomMainActivity, Observer { notes ->
            notes?.let {
                noteListAdapter.setNotes(notes)
            }
        })
    }

    override fun onDeleteClickListener(myNote: Note) {
        noteViewModel.delete(myNote)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Code to insert note
            val noteId = UUID.randomUUID().toString()
            val note = Note(noteId, data!!.getStringExtra(NewNoteActivity.NOTE_ADDED))
            noteViewModel.insert(note)

            Toast.makeText(applicationContext, R.string.saved, Toast.LENGTH_LONG).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // Code to update the note
            val note = Note(
                data!!.getStringExtra(EditNoteActivity.NOTE_ID),
                data.getStringExtra(EditNoteActivity.UPDATE_NOTE)
            )
            noteViewModel.update(note)

            Toast.makeText(applicationContext, R.string.updated, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, R.string.not_saved, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }

}

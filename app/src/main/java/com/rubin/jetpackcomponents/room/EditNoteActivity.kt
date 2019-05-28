package com.rubin.jetpackcomponents.room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_new_room.*

class EditNoteActivity : AppCompatActivity() {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_room)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            id = bundle.getString("note_id")
            val note = bundle.getString("note")
            etNote.setText(note)
        }

        btnSave.setOnClickListener {

            val updatedNote = etNote!!.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(NOTE_ID, id)
            resultIntent.putExtra(UPDATE_NOTE, updatedNote)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        val NOTE_ID = "note_id"
        internal val UPDATE_NOTE = "note_text"
    }
}
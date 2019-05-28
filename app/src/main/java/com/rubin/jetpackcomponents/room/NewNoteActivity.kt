package com.rubin.jetpackcomponents.room

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_new_room.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_room)

        btnSave.setOnClickListener {
            val resultIntent = Intent()

            if (TextUtils.isEmpty(etNote.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                val note = etNote.text.toString()
                resultIntent.putExtra(NOTE_ADDED, note)
                setResult(Activity.RESULT_OK, resultIntent)
            }

            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val NOTE_ADDED = "new_note"
    }
}
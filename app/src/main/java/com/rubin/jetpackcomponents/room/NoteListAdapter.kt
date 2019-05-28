package com.rubin.jetpackcomponents.room

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.list_item_room.view.*

class NoteListAdapter(private val mContext: Context, private val onDeleteClickListener: OnDeleteClickListener) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private var noteList: List<Note> = mutableListOf()

    interface OnDeleteClickListener {
        fun onDeleteClickListener(myNote: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_room, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount() = noteList.count()

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.setData(note.note, position)
        holder.setListeners()
    }

    fun setNotes(notes: List<Note>) {
        noteList = notes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pos: Int = 0

        fun setData(note: String, position: Int) {
            itemView.txtNote.text = note
            pos = position
        }

        fun setListeners() {
            itemView.setOnClickListener {
                val intent = Intent(mContext, EditNoteActivity::class.java)
                intent.putExtra("note_id", noteList[pos].id)
                intent.putExtra("note", noteList[pos].note)
                (mContext as Activity).startActivityForResult(
                    intent,
                    RoomMainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE
                )
            }

            itemView.imgDelete.setOnClickListener {
                onDeleteClickListener.onDeleteClickListener(noteList[pos])
            }
        }
    }
}
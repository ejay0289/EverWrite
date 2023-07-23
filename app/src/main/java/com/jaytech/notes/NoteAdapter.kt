package com.jaytech.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    val noteClickInterface: NoteClickInterface,
    val noteClickDeleteInterface: NoteClickDeleteInterface):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    val notes = ArrayList<Note>()
    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val tvNoteTitle =itemView.findViewById<TextView>(R.id.tvNoteTitle)
        val ivDelete =itemView.findViewById<ImageView>(R.id.ivDelete)
        val tvTimestamp =itemView.findViewById<TextView>(R.id.tvTimestamp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.apply {
            tvNoteTitle.text = notes[position].noteTitle
            tvTimestamp.text = notes[position].timeStamp


            ivDelete.setOnClickListener{
                noteClickDeleteInterface.onDeleteClick(notes.get(position))
            }
            itemView.setOnClickListener {
                noteClickInterface.onNoteClick(notes.get(position))
            }

        }

    }

    fun updateList(newList:List<Note>){
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }
}


interface NoteClickDeleteInterface{
    fun onDeleteClick(note:Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}


package com.jaytech.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var rvNotes:RecyclerView
    lateinit var btnAdd:FloatingActionButton
    lateinit var viewModel:NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd =findViewById(R.id.btnAdd)
        rvNotes = findViewById(R.id.rvNotes)
        rvNotes.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this,this)
        rvNotes.adapter = noteAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteAdapter.updateList(it)
            }
        }
        btnAdd.setOnClickListener{
            val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity,AddEditNoteActivity::class.java)
       intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteId",note.id)

        startActivity(intent)
        this.finish()
    }

    override fun onDeleteClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} deleted",Toast.LENGTH_SHORT).show()

    }
}
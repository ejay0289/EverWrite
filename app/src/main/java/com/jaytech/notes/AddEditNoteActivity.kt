package com.jaytech.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var btnAddUpdate:Button
    lateinit var etTitle:EditText
    lateinit var etNote:EditText
    lateinit var viewModel: NoteViewModel
    var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        btnAddUpdate = findViewById(R.id.btnAddUpdate)
        etTitle = findViewById(R.id.etNoteTitle)
        etNote = findViewById(R.id.etNoteDescription)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteId =intent.getIntExtra("noteId",-1)

            btnAddUpdate.text = "Update Note"
            etTitle.setText(noteTitle)
            etNote.setText(noteDescription)


        }
        else{
            btnAddUpdate.text = "Save Note"
        }

        btnAddUpdate.setOnClickListener {
            val noteTitle = etTitle.text.toString()
            val noteDesciption = etNote.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDesciption.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDesciption, currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                }
            }else{
                if(noteTitle.isNotEmpty() && noteDesciption.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDesciption,currentDate))
                }
                startActivity(Intent(applicationContext,MainActivity::class.java))
                this.finish()
            }
        }
    }
}
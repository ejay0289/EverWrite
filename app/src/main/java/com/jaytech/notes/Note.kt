package com.jaytech.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    @ColumnInfo(name = "title")var noteTitle:String,
    @ColumnInfo(name = "description")var noteDescription:String
    ,@ColumnInfo(name = "timestamp")var timeStamp:String
){
    @PrimaryKey(autoGenerate = true) var id = 0

}
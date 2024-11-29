package com.example.acoustic.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "song_db")
data class SongEntity (
    @PrimaryKey
    val id:Int,
    val name:String,
    val liked:Boolean,
    
)
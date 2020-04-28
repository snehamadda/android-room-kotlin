package com.example.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var task: String,
    var description: String
) : Serializable
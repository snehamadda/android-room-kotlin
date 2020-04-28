package com.example.roomdatabase.dao

import androidx.room.*
import com.example.roomdatabase.models.Task

@Dao
interface TaskDao {
    @Insert
    fun saveTasks(task: Task)

    @Query(value = "Select * from Task")
    fun getAllTasks() : MutableList<Task>
}

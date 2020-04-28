package com.example.roomdatabase.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roomdatabase.R
import com.example.roomdatabase.adapters.TasksAdapter
import com.example.roomdatabase.database.TaskDatabase
import com.example.roomdatabase.models.Task
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    private lateinit var tasksAdapter: TasksAdapter
    private val taskList = mutableListOf<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasksAdapter = TasksAdapter(taskList)
        recyclerViewList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList.adapter = tasksAdapter

        addButton.setOnClickListener {
            val addTaskIntent = Intent(this, AddTaskActivity::class.java)
            startActivity(addTaskIntent)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java, "taskdb.db"
        ).build()

    }

    private fun getAllTasks() {
        val db = TaskDatabase(this)
        doAsync {
            val allTasks = db.taskDao()?.getAllTasks()
            runOnUiThread {
                if (allTasks?.isNotEmpty() == true) {
                    taskList.clear()
                    taskList.addAll(allTasks)
                    tasksAdapter.notifyDataSetChanged()
                } else {
                    // Toast no tasks found
                    Toast.makeText(this@MainActivity, "No tasks to display", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllTasks()
    }
}

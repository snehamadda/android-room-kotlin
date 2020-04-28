package com.example.roomdatabase.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabase.R
import com.example.roomdatabase.database.TaskDatabase
import com.example.roomdatabase.models.Task
import kotlinx.android.synthetic.main.add_list_task.*
import org.jetbrains.anko.doAsync

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_list_task)

        saveButton.setOnClickListener {
            val title = taskET.text.toString()
            val description = desriptionET.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(this, "Please enter title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            saveTask(title, description)
        }

    }

    private fun saveTask(title: String, description: String) {
        val task = Task(task = title, description = description)
        val db = TaskDatabase(this)
        doAsync {
            db.taskDao()?.saveTasks(task)
            runOnUiThread {
                finish()
            }
        }
    }
}

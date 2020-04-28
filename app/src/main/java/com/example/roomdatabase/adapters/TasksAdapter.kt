package com.example.roomdatabase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.models.Task
import kotlinx.android.synthetic.main.activity_add_task2.view.*

class TasksAdapter(val taskList: List<Task>) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_add_task2, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tasks: Task = taskList[position]
        holder.idTV.text = "${position + 1}"
        holder.taskTV.text = tasks.task
        holder.descriptionTV.text = tasks.description
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTV = itemView.idTV
        val taskTV = itemView.taskTV
        val descriptionTV = itemView.descriptionTV
    }

}
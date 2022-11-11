package com.hunguet123.todoList.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hunguet123.todoList.R
import com.hunguet123.todoList.data.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class TaskViewHolder(itemView: View) : ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindData(task: Task) {
            itemView.textTitle.text = (adapterPosition + 1).toString() + ". " + task.title
            itemView.textDescription.text = task.description
        }
    }

    class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
}

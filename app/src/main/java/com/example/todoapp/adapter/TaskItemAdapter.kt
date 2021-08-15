package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.TaskItem
import com.example.todoapp.databinding.TaskItemBinding

class TaskItemAdapter(): ListAdapter<TaskItem, TaskItemAdapter.TaskViewHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<TaskItem>(){
        override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem) =
            oldItem == newItem

    }

    class TaskViewHolder(val binding : TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(task: TaskItem){
            binding.apply {
                checkboxItemCompleted.isChecked = task.isDone
                textViewItemName.text = task.name
                textViewItemName.paint.isStrikeThruText = task.isDone
                imageViewImportant.isVisible = task.isImportant
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
package com.example.todoapp.repository

import androidx.room.Database
import com.example.todoapp.data.TaskItem

@Database(entities = [TaskItem::class],version = 1)
abstract class TaskItemDatabase {

    abstract fun taskItemDao() : TaskItemDao
}
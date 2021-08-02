package com.example.todoapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.TaskItem

@Database(entities = [TaskItem::class],version = 1)
abstract class TaskItemDatabase: RoomDatabase() {

    abstract fun taskItemDao() : TaskItemDao
}
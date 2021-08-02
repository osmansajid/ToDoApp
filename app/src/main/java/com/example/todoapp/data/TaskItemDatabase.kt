package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.TaskItem
import com.example.todoapp.data.TaskItemDao

@Database(entities = [TaskItem::class],version = 1)
abstract class TaskItemDatabase: RoomDatabase() {

    abstract fun taskItemDao() : TaskItemDao
}
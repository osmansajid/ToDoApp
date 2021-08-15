package com.example.todoapp.repository

import com.example.todoapp.data.TaskItem
import com.example.todoapp.data.TaskItemDao
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskItemDao: TaskItemDao) {

    suspend fun insert(task: TaskItem){
        taskItemDao.insert(task)
    }

    suspend fun update(task: TaskItem){
        taskItemDao.update(task)
    }

    suspend fun delete(task: TaskItem){
        taskItemDao.delete(task)
    }

    fun getAllTask() = taskItemDao.getAllItem()
}
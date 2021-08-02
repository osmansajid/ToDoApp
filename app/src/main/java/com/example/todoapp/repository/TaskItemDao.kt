package com.example.todoapp.repository

import androidx.room.*
import com.example.todoapp.data.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task:TaskItem)

    @Delete
    suspend fun  delete(task: TaskItem)

    @Update
    suspend fun  update(task: TaskItem)

    @Query("SELECT * FROM TASKITEM")
    fun getAllItem(): Flow<List<TaskItem>>
}
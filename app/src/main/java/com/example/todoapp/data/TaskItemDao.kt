package com.example.todoapp.data

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

    @Query("SELECT * FROM TASKITEM WHERE NAME LIKE '%' || :searchQuery || '%' ORDER BY ISIMPORTANT DESC")
    fun getAllItem(searchQuery: String): Flow<List<TaskItem>>
}
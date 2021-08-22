package com.example.todoapp.data

import androidx.room.*
import com.example.todoapp.data.TaskItem
import com.example.todoapp.ui.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task:TaskItem)

    @Delete
    suspend fun  delete(task: TaskItem)

    @Update
    suspend fun  update(task: TaskItem)

    fun getAllItem(searchQuery: String,sortby : SortOrder,hideCompleted: Boolean): Flow<List<TaskItem>> =
        when(sortby){
            SortOrder.BY_NAME-> getAllItemSortByName(searchQuery,hideCompleted)
            SortOrder.BY_CREATED_DATE-> getAllItemSortByDate(searchQuery,hideCompleted)
        }

    @Query("SELECT * FROM TASKITEM WHERE (NAME LIKE '%' || :searchQuery || '%') AND (ISDONE != :hideCompleted OR ISDONE = 0) ORDER BY ISIMPORTANT DESC , NAME ASC")
    fun getAllItemSortByName(searchQuery: String,hideCompleted: Boolean): Flow<List<TaskItem>>

    @Query("SELECT * FROM TASKITEM WHERE (NAME LIKE '%' || :searchQuery || '%') AND (ISDONE != :hideCompleted OR ISDONE = 0) ORDER BY ISIMPORTANT DESC , CREATEDAT ASC")
    fun getAllItemSortByDate(searchQuery: String,hideCompleted: Boolean): Flow<List<TaskItem>>
}
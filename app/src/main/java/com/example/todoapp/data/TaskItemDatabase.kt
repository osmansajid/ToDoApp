package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.data.TaskItem
import com.example.todoapp.data.TaskItemDao
import com.example.todoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [TaskItem::class],version = 1)
abstract class TaskItemDatabase: RoomDatabase() {

    abstract fun taskItemDao() : TaskItemDao

    class Callback @Inject constructor(@ApplicationScope private val coroutineScope: CoroutineScope,
                                       private val taskItemDatabase: Provider<TaskItemDatabase>
                                       ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = taskItemDatabase.get().taskItemDao()
            coroutineScope.launch {
                dao.insert(TaskItem("Read books"))
                dao.insert(TaskItem("Go for walk"))
                dao.insert(TaskItem("Complete assignments"))
                dao.insert(TaskItem("Complete project work",isImportant = true))
            }
        }
    }
}
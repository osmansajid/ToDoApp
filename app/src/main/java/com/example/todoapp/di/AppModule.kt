package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.data.TaskItemDao
import com.example.todoapp.data.TaskItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskItemDb(app: Application,callback: RoomDatabase.Callback): TaskItemDatabase = Room.databaseBuilder(app,
        TaskItemDatabase::class.java,"TASK_DATABASE")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskItemDao(taskDb : TaskItemDatabase): TaskItemDao = taskDb.taskItemDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScopeCoroutine() = CoroutineScope(SupervisorJob())
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope
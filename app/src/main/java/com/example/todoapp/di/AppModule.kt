package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.repository.TaskItemDao
import com.example.todoapp.repository.TaskItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskItemDb(app: Application): TaskItemDatabase = Room.databaseBuilder(app,TaskItemDatabase::class.java,"TASK_DATABASE")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTaskItemDao(taskDb : TaskItemDatabase): TaskItemDao = taskDb.taskItemDao()
}
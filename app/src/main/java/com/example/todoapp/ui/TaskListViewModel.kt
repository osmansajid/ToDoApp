package com.example.todoapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todoapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject  constructor(private val repository: TaskRepository): ViewModel() {
    val searchQuery = MutableStateFlow("")

    private val taskFlow = searchQuery.flatMapLatest {
        repository.getAllTask(it)
    }

    val allTask = taskFlow.asLiveData()
}
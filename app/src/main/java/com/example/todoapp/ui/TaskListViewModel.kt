package com.example.todoapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todoapp.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject  constructor(private val repository: TaskRepository): ViewModel() {
    val searchQuery = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.BY_CREATED_DATE)
    val hideCompleted = MutableStateFlow(false)

    private val taskFlow = combine(searchQuery,sortOrder,hideCompleted) {query,sortOrder,hide->
        Triple(query,sortOrder,hide)
    }.flatMapLatest {(query,sortOrder,hide)->
            repository.getAllTask(query,sortOrder,hide)
    }

    val allTask = taskFlow.asLiveData()
}

enum class SortOrder{ BY_NAME, BY_CREATED_DATE}
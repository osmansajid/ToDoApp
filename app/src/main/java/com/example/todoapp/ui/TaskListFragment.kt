package com.example.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TaskItemAdapter
import com.example.todoapp.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment: Fragment(R.layout.fragment_task_list) {

    private val viewModel : TaskListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTaskListBinding.bind(view)

        val taskAdapter = TaskItemAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.allTask.observe(viewLifecycleOwner) {
            Log.d("ToDoApp", "onViewCreated: size "+it.size)
            taskAdapter.submitList(it)
        }

    }
}
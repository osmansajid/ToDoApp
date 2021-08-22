package com.example.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TaskItemAdapter
import com.example.todoapp.databinding.FragmentTaskListBinding
import com.example.todoapp.utils.onQueryTextChangeListener
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

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.task_list_menu,menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChangeListener {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_search->{
                //to be implemented
                return true
            }
            R.id.action_sort_by_name->{
                viewModel.sortOrder.value = SortOrder.BY_NAME
                return true
            }
            R.id.action_sort_by_date->{
                viewModel.sortOrder.value = SortOrder.BY_CREATED_DATE
                return true
            }
            R.id.action_hide_completed->{
                item.isChecked = !item.isChecked
                viewModel.hideCompleted.value = item.isChecked
                return true
            }
            R.id.action_delete_completed->{
                //to be implemented
                return true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}
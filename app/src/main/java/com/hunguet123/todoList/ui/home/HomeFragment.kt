package com.hunguet123.todoList.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hunguet123.todoList.R

import com.hunguet123.todoList.ui.add.AddFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val taskAdapter = TaskAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        listenEvents()
        initData()
        observeData()
    }

    private fun setupViews() {
        recyclerTasks.adapter = taskAdapter
    }

    private fun listenEvents() {
        cardAdd.setOnClickListener {
            val fragment = AddFragment().apply {
                onPopBackstack = {
                    viewModel.getTasks()
                }
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.frameMain, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun initData() {
        viewModel.getTasks()
    }

    private fun observeData() {
        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            context?.run {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.submitList(tasks)
            textCount.text = getString(R.string.text_count, tasks.size)
        }
    }
}

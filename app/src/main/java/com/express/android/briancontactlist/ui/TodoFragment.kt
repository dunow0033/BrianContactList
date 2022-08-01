package com.express.android.briancontactlist.ui

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.express.android.briancontactlist.adapters.TodoAdapter
import com.express.android.briancontactlist.databinding.FragmentTodoBinding
import com.express.android.briancontactlist.db.TodoDatabase
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.repository.TodoRepository


class TodoFragment : Fragment(), TodoAdapter.HandleItemClick {

    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            requireActivity().application,
            TodoRepository(TodoDatabase(requireActivity()))
        )
    }

    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter(viewModel, this@TodoFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnAddTodo.setOnClickListener {
                val todo = etTodo.editText?.text.toString()
                viewModel.addTodo(Todo(todoTask = todo))
                etTodo.editText?.setText("")
            }
            rvTodo.apply {
                adapter = todoAdapter
                rvTodo.layoutManager?.let {
                    addItemDecoration(DividerItemDecoration(requireContext(), it.layoutDirection))
                }
            }

            viewModel.todos.observe(viewLifecycleOwner) {
                todoAdapter.differ.submitList(it)
            }
        }
    }

    private fun showEditTodoDialog() {
        val dialogBuilder: AlertDialog = Builder(this).create()
        val dialogView: View = layoutInflater.inflate(R.layout.add_category_layout, null)
        val enterCategoryInput = dialogView.findViewById<EditText>(R.id.enterCategoryInput)
        val createButton = dialogView.findViewById<TextView>(R.id.createButton)
        val cancelButton = dialogView.findViewById<TextView>(R.id.cancelButton)
        cancelButton.setOnClickListener { dialogBuilder.dismiss() }
        createButton.setOnClickListener(View.OnClickListener {
            val name = enterCategoryInput.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this@MainActivity, "Enter category name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            categoryForEdit.categoryName = name
            viewModel.updateCategory(categoryForEdit)
            dialogBuilder.dismiss()
        })
        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }

    override fun itemClick(todo: Todo) {
        TODO("Not yet implemented")
    }

    override fun removeItem(todo: Todo) {
        TODO("Not yet implemented")
    }

    override fun editItem(todo: Todo) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.express.android.briancontactlist.ui

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.express.android.briancontactlist.MainActivity
import com.express.android.briancontactlist.adapters.TodoAdapter
import com.express.android.briancontactlist.databinding.FragmentTodoBinding
import com.express.android.briancontactlist.databinding.ListItemTodoBinding
import com.express.android.briancontactlist.db.TodoDatabase
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.repository.TodoRepository


class TodoFragment : Fragment(), TodoAdapter.HandleItemClick {

    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private var adapter: TodoAdapter? = null
    private var todo: Todo? = null

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            requireActivity().application,
            TodoRepository(TodoDatabase(requireActivity()))
        )
    }

    //val db: TodoDatabase

    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter(viewModel, this@TodoFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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



    private fun showEditTodoDialog(int: Int) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())

        //dialogBuilder.setTitle(viewModel.todos.value?.get(int)?.id.toString())

        dialogBuilder.setMessage("Edit Todo #${viewModel.todos.value?.get(int)?.id}")

        var updatedText = ""
        var updatedTodo = EditText(requireActivity())
        updatedTodo.setText(viewModel.todos.value?.get(int)?.todoTask)
        updatedTodo.setInputType(InputType.TYPE_CLASS_TEXT)
        dialogBuilder.setView(updatedTodo)

        dialogBuilder.setCancelable(true)
        dialogBuilder.setPositiveButton("OK") { dialogInterface, it ->
            updatedText = updatedTodo.text.toString()

            val todoModel = Todo(viewModel.todos.value?.get(int)?.id, updatedText)
            viewModel.updateTodo(todoModel)
            dialogInterface.dismiss()
        }

        dialogBuilder.setNegativeButton("Cancel") { dialogInterface, it ->
            dialogInterface.dismiss()
        }

        //viewModel.todos.value?.get(int)?.todoTask = updatedText

        //dialogBuilder.setMessage(viewModel.todos.value?.get(int)?.todoTask)
        //viewModel.todos.value?.get(int)?.todoTask?.let { viewModel.updateTodo(it) }
        dialogBuilder.create()
        dialogBuilder.show()
//        val dialogBuilder: AlertDialog = AlertDialog.Builder(requireContext()).create()
//        val dialogView = layoutInflater.inflate(R.layout.list_item_todo, null)
////        val enterCategoryInput = dialogView.findViewById<EditText>(R.id.enterCategoryInput)
//        //val id =
//        val editButton = dialogView.findViewById<TextView>(R.id.editBtn)
//        val deleteButton = dialogView.findViewById<TextView>(R.id.deleteBtn)
//        deleteButton.setOnClickListener { dialogBuilder.dismiss() }
//        editButton.setOnClickListener(View.OnClickListener {
//            dialogBuilder.setTitle("Edit your todo")
//            //val name = enterCategoryInput.text.toString()
////            if (TextUtils.isEmpty(name)) {
////                Toast.makeText(requireContext(), "Enter category name", Toast.LENGTH_SHORT).show()
////                return@OnClickListener
////            }
////            categoryForEdit.categoryName = name
////            viewModel.updateCategory(categoryForEdit)
//            dialogBuilder.dismiss()
//        })
//        dialogBuilder.setView(dialogView)
//        dialogBuilder.show()
    }

    override fun itemClick(todo: Todo) {
        TODO("Not yet implemented")
    }

//    override fun removeItem(todo: Todo) {
//        TODO("Not yet implemented")
//    }

    override fun editItem(int: Int) {
        showEditTodoDialog(int)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
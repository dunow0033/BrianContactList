package com.express.android.briancontactlist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.express.android.briancontactlist.MainActivity
import com.express.android.briancontactlist.databinding.ActivityMainBinding
import com.express.android.briancontactlist.databinding.FragmentTodoBinding
import com.express.android.briancontactlist.databinding.ListItemTodoBinding
import com.express.android.briancontactlist.db.TodoDatabase
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.repository.TodoRepository

class ListItemFragment : Fragment() {

    private var _binding: ListItemTodoBinding? = null
    private val binding: ListItemTodoBinding get() = _binding!!

//    private val viewModel: TodoViewModel by viewModels {
//        TodoViewModelFactory(
//            requireActivity().application,
//            TodoRepository(TodoDatabase(requireActivity()))
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListItemTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        with(binding) {
//            editBtn.setOnClickListener {
//                //Toast.makeText(context, "${ tvTodo.text } updated!!", Toast.LENGTH_LONG).show()
//                //todoBinding.etTodo.editText?.setText("updated!!")
//                Log.d("updated", "Edit clicked!!")
//            }
//            deleteBtn.setOnClickListener {
//                viewModel.deleteTodo(todo = to)
//            }
//        }
    }
}
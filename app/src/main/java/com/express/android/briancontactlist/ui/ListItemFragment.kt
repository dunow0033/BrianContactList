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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListItemTodoBinding.inflate(inflater, container, false)
        return binding.root
    }
}
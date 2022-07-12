package com.express.android.briancontactlist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(
    app: Application,
    private val repo: TodoRepository
) : AndroidViewModel(app){

    private var _todos: MutableLiveData<Todo>> = MutableLiveData()
    val todos: LiveData<List<Todo>> = _todos

    init {

    }

    private fun getAllTodos() {
        viewModelScope.launch {

        }
    }
}
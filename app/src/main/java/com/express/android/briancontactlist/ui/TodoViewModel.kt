package com.express.android.briancontactlist.ui

import android.app.Application
import androidx.lifecycle.*
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.repository.TodoRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.security.AccessController.getContext

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect

class TodoViewModel(
    app: Application,
    private val repo: TodoRepository
) : AndroidViewModel(app){

    private var _todos: MutableLiveData<List<Todo>> = MutableLiveData()
    val todos: LiveData<List<Todo>> = _todos

    init {
        getAllTodos()
    }

    private fun getAllTodos() {
        viewModelScope.launch {
            repo.getTodos().collect {
                _todos.postValue(it)
            }
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            repo.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repo.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repo.updateTodo(todo)
        }
    }

}

class TodoViewModelFactory(
    private val app: Application,
    private val repo: TodoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(app, repo) as T
        } else {
            throw IllegalArgumentException("Instance of TodoViewModel cannot be created!!")
        }
    }
}
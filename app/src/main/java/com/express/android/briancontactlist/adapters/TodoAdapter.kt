package com.express.android.briancontactlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.express.android.briancontactlist.databinding.ListItemTodoBinding
import com.express.android.briancontactlist.model.Todo
import com.express.android.briancontactlist.ui.TodoViewModel

class TodoAdapter(private val viewModel: TodoViewModel, clickListener: HandleItemClick) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private lateinit var clickListener: HandleItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ListItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(differ.currentList[position])

//        holder.binding.deleteBtn.setOnClickListener(View.OnClickListener {
//            fun onClick(view: View) {
//                viewModel.deleteTodo(currentContact)
//            }
//        })

        holder.binding.editBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                clickListener.editItem(differ.currentList[position])
            }
        })
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.todoTask == newItem.todoTask
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    class TodoViewHolder(val binding: ListItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvTodo.text = todo.todoTask
        }
    }

    interface HandleItemClick {
        fun itemClick(todo: Todo)
        fun removeItem(todo: Todo)
        fun editItem(todo: Todo)
    }
}
package kz.temir.todolistapp.todo_list.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.temir.todolistapp.databinding.ItemTodoBinding
import kz.temir.todolistapp.todo_list.presentation.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoItems: List<Todo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged() // TODO: resolve
        }

    class TodoViewHolder(
        private val todoBinding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(todoBinding.root) {
        fun bind(todo: Todo) {
            with(todoBinding) {
                titleTv.text = todo.title
                descriptionTv.text = todo.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount() = todoItems.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = todoItems[position]
        holder.bind(item)
    }
}

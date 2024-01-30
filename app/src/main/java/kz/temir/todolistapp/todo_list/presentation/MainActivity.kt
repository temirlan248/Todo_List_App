package kz.temir.todolistapp.todo_list.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kz.temir.todolistapp.R
import kz.temir.todolistapp.databinding.ActivityMainBinding
import kz.temir.todolistapp.todo.TodoActivity
import kz.temir.todolistapp.todo_list.presentation.rv.TodoAdapter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoAdapter = TodoAdapter(
            onItemClick = { todo ->
                startActivity(TodoActivity.newInstance(this, todo.id, todo.title, todo.description))
            },
            onLongPress = { todo ->
                AlertDialog.Builder(this)
                    .setTitle("Delete Todo")
                    .setMessage("Do you want to delete Todo?")
                    .setPositiveButton(
                        "Delete"
                    ) { _, _ ->
                        viewModel.deleteTodo(todo)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.todoRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL).apply {
            val divider = ResourcesCompat.getDrawable(resources, R.drawable.divider_todo, null)
            divider?.let { setDrawable(it) }
        })
        binding.todoRv.adapter = todoAdapter
        viewModel.todos.observe(this) { todoItems ->
            todoAdapter.todoItems = todoItems
        }
        binding.fab.setOnClickListener {
            startActivity(TodoActivity.newInstance(this))
        }
    }
}

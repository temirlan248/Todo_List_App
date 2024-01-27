package kz.temir.todolistapp.todo_list.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kz.temir.todolistapp.databinding.ActivityMainBinding
import kz.temir.todolistapp.todo_list.presentation.rv.TodoAdapter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoAdapter = TodoAdapter()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayout.VERTICAL,
        )
        binding.todoRv.addItemDecoration(dividerItemDecoration)
        binding.todoRv.adapter = todoAdapter
        viewModel.todos.observe(this) { todoItems ->
            todoAdapter.todoItems = todoItems
        }
        binding.fab.setOnClickListener {
            viewModel.addTodo()
        }
    }
}

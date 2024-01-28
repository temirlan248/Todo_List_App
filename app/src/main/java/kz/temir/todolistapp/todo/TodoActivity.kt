package kz.temir.todolistapp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kz.temir.todolistapp.databinding.ActivityTodoBinding

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    private val viewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent?.extras?.getString(ID_KEY)
        val title = intent?.extras?.getString(TITLE_KEY)
        val description = intent?.extras?.getString(DESCRIPTION_KEY)

        viewModel.exitEvent.observe(this){
            if (it){
                finish()
            }
        }

        with(binding) {
            titleEt.setText(title)
            descriptionEt.setText(description)
            topBar.title = if (id == null) "Add Todo" else "Edit Todo"

            topBar.setNavigationOnClickListener { finish() }
            submitBtn.setOnClickListener {
                viewModel.updateTodo(id, titleEt.text.toString(), descriptionEt.text.toString())
            }
        }
    }

    companion object {
        private const val ID_KEY = "ID"
        private const val TITLE_KEY = "TITLE"
        private const val DESCRIPTION_KEY = "DESCRIPTION"
        fun newInstance(
            from: Activity,
            id: String? = null,
            title: String? = null,
            description: String? = null
        ): Intent {
            return Intent(from, TodoActivity::class.java).apply {
                putExtra(ID_KEY, id)
                putExtra(TITLE_KEY, title)
                putExtra(DESCRIPTION_KEY, description)
            }
        }
    }
}

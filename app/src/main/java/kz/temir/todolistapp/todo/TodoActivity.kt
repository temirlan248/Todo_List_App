package kz.temir.todolistapp.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kz.temir.todolistapp.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            topBar.setNavigationOnClickListener { finish() }

            submitBtn.setOnClickListener {
                Toast.makeText(
                    this@TodoActivity,
                    "${titleEt.text} - ${descriptionEt.text}",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }
}

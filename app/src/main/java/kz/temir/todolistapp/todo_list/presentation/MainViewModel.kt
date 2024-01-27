package kz.temir.todolistapp.todo_list.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.temir.todolistapp.todo_list.data.TodoDao
import kz.temir.todolistapp.todo_list.data.TodoTable
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoDao: TodoDao,
) : ViewModel() {
    val todos = MutableLiveData<List<Todo>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.getAll().collectLatest { todoDbos ->
                todos.postValue(
                    todoDbos.map { todo ->
                        Todo(
                            id = todo.id,
                            title = todo.title,
                            description = todo.description,
                        )
                    }
                )
            }
        }
    }

    fun addTodo() {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insert(
                TodoTable(
                    id = UUID.randomUUID().toString(),
                    title = UUID.randomUUID().toString(),
                    description = UUID.randomUUID().toString() + "::::" + UUID.randomUUID()
                        .toString(),
                )
            )
        }
    }
}

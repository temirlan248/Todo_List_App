package kz.temir.todolistapp.todoList.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.temir.todolistapp.todoList.data.TodoDao
import kz.temir.todolistapp.todoList.data.TodoTable
import kz.temir.todolistapp.todoList.presentation.models.Todo

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
                        return@map Todo(
                            id = todo.id,
                            title = todo.title,
                            description = todo.description,
                        )
                    },
                )
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.delete(
                TodoTable(
                    id = todo.id,
                    title = todo.title,
                    description = todo.description,
                ),
            )
        }
    }
}

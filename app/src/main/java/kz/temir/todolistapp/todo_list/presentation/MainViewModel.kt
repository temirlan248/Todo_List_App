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
import kz.temir.todolistapp.todo_list.presentation.models.Todo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoDao: TodoDao,
) : ViewModel() {
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.delete(
                TodoTable(
                    id = todo.id,
                    title = todo.title,
                    description = todo.description
                )
            )
        }
    }

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
}

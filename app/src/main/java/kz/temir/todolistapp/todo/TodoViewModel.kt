package kz.temir.todolistapp.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.temir.todolistapp.todoList.data.TodoDao
import kz.temir.todolistapp.todoList.data.TodoTable

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoDao: TodoDao,
) : ViewModel() {
    val exitEvent = MutableLiveData(false)

    fun updateTodo(id: String?, title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id == null) {
                todoDao.insert(
                    TodoTable(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        description = description,
                    ),
                )
            } else {
                todoDao.update(
                    TodoTable(
                        id = id,
                        title = title,
                        description = description,
                    ),
                )
            }
            exitEvent.postValue(true)
        }
    }
}

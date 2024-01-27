package kz.temir.todolistapp.todo_list.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO")
    fun getAll(): Flow<List<TodoTable>>

    @Insert
    fun insert(todo: TodoTable)

    @Update
    fun update(todo: TodoTable)

    @Delete
    fun delete(todo: TodoTable)
}

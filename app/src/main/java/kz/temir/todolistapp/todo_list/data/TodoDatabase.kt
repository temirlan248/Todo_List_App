package kz.temir.todolistapp.todo_list.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTable::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

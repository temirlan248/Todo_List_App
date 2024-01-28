package kz.temir.todolistapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.temir.todolistapp.todo_list.data.TodoDao
import kz.temir.todolistapp.todo_list.data.TodoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TodoDatabase {
        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java,
            "TodoDatabase"
        ).build()
    }

    @Provides
    fun provideTodoDao(appDatabase: TodoDatabase): TodoDao = appDatabase.todoDao()
}

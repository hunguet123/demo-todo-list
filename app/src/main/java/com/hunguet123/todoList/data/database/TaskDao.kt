package com.hunguet123.todoList.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hunguet123.todoList.data.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)
}

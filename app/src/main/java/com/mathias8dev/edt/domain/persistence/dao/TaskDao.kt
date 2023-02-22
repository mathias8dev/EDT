package com.mathias8dev.edt.domain.persistence.dao

import androidx.room.*
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import com.mathias8dev.edt.domain.persistence.model.Task


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY ending_datetime ASC")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks INNER JOIN categories ON tasks.category_id = categories.id WHERE categories.title LIKE :categoryTitle")
    fun getAllTasksByCategoryTitle(categoryTitle: String): List<Task>

    @Transaction
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM tasks INNER JOIN categories ON tasks.category_id = categories.id WHERE categories.title LIKE :categoryTitle")
    fun getCategoryAndTaskByCategoryTitle(categoryTitle: String): CategoryAndTask?

    @Transaction
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM tasks WHERE tasks.category_id = :categoryId")
    fun getTasksByCategoryId(categoryId: Long): List<Task>

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    fun deleteTaskById(taskId: Long)

    @Query("DELETE FROM tasks WHERE is_terminated = true")
    fun deleteTerminatedTasks()

    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun findById(id: Long): Task?
}
package com.mathias8dev.edt.domain.persistence.repository

import androidx.lifecycle.MutableLiveData
import com.mathias8dev.edt.domain.persistence.dao.TaskDao
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import com.mathias8dev.edt.domain.persistence.model.Task
import kotlinx.coroutines.*
import javax.inject.Inject


class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val allTasks = MutableLiveData<List<Task>>()
    val allTasksByCategoryId = MutableLiveData<List<Task>>()
    val foundTaskById = MutableLiveData<Task>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTask(newTask: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.insertTask(newTask)
        }
    }

    fun deleteTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    fun deleteTaskById(taskId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTaskById(taskId)
        }
    }

    fun updateTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }


    fun getAllTasks() {
        coroutineScope.launch(Dispatchers.IO) {
            allTasks.value = taskDao.getAllTasks()
        }
    }

    fun getTasksByCategoryId(categoryId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            allTasksByCategoryId.value = aysncFind(categoryId).await()
        }
    }

    private fun aysncFind(categoryId: Long): Deferred<List<Task>> = coroutineScope.async {
        return@async taskDao.getTasksByCategoryId(categoryId)
    }

    fun deleteTerminatedTasks() {
        coroutineScope.launch(Dispatchers.IO) { taskDao.deleteTerminatedTasks() }
    }

    fun findTaskById(id: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            foundTaskById.value = taskDao.findById(id)
        }

    }

    suspend fun findTaskById2(id: Long): Task? = taskDao.findById(id)


}
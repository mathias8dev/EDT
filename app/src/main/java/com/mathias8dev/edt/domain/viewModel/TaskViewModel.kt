package com.mathias8dev.edt.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import com.mathias8dev.edt.domain.persistence.model.Task
import com.mathias8dev.edt.domain.persistence.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val allTasks: LiveData<List<Task>> = taskRepository.allTasks
    val allTasksByCategoryId: LiveData<List<Task>> = taskRepository.allTasksByCategoryId
    val foundTaskById: LiveData<Task?> = taskRepository.foundTaskById

    fun getAllTasks() {
        taskRepository.getAllTasks()
    }

    fun getTasksByCategoryId(categoryId: Long) {
        taskRepository.getTasksByCategoryId(categoryId)
    }


    fun insertTask(newTask: Task) {
        taskRepository.insertTask(newTask)
    }

    fun deleteTask(task: Task) {
        taskRepository.deleteTask(task)
    }

    fun deleteTaskById(id: Long) {
        taskRepository.deleteTaskById(id)
    }

    fun findTaskById(id: Long) = taskRepository.findTaskById(id)

    fun updateTask(task: Task) {
        taskRepository.updateTask(task)
    }

    fun deleteTerminatedTasks() {

        taskRepository.deleteTerminatedTasks()
    }

    suspend fun foundTaskById(taskId: Long): Task? = taskRepository.findTaskById2(taskId)
}
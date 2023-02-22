package com.mathias8dev.edt.domain.persistence.repository

import androidx.lifecycle.MutableLiveData
import com.mathias8dev.edt.domain.persistence.dao.EdtDao
import com.mathias8dev.edt.domain.persistence.dao.TaskDao
import com.mathias8dev.edt.domain.persistence.model.Edt
import com.mathias8dev.edt.domain.persistence.model.EdtAndTask
import kotlinx.coroutines.*
import javax.inject.Inject

class EdtRepository @Inject constructor (private val edtDao: EdtDao, private val taskDao: TaskDao){

    val allEdts = MutableLiveData<List<Edt>>()
    val foundEdt = MutableLiveData<List<Edt>>()
    val foundEdtAndTasks = MutableLiveData<List<EdtAndTask>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertEdt(newEdt: Edt) {
        coroutineScope.launch(Dispatchers.IO) {
            edtDao.insertEdt(newEdt)
        }
    }

    fun deleteEdt(edt: Edt) {
        coroutineScope.launch(Dispatchers.IO) {
            edtDao.deleteEdt(edt)
        }
    }

    fun updateEdt(edt: Edt) {
        coroutineScope.launch(Dispatchers.IO) {
            edtDao.updateEdt(edt)
        }
    }

    fun findEdtByDayNameAndWeekName(dayName: String, weekName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundEdt.value = asyncFind(dayName, weekName).await()
        }
    }

    fun getAllEdts() {
        coroutineScope.launch(Dispatchers.IO) {
            allEdts.value = edtDao.getAllEdtsOrderByStartingTime()
        }
    }

    fun getAllEdtsWithTasksByDayNameAndWeekName(dayName: String, weekName: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundEdtAndTasks.value = asyncFind2(dayName, weekName).await()
        }
    }

    private fun asyncFind(dayName: String, weekName: String): Deferred<List<Edt>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async edtDao.getAllEdtsByDayNameAndWeekNameOrderByStartingTime(dayName, weekName)
        }

    private fun asyncFind2(dayName: String, weekName: String): Deferred<List<EdtAndTask>> =
        coroutineScope.async(Dispatchers.IO) {
            return@async edtDao.getAllEdtsByDayNameAndWeekNameOrderByStartingTime(dayName, weekName).map {
                val tasks = taskDao.getAllTasksByCategoryTitle(it.title)
                return@map EdtAndTask(it, tasks)
            }
        }

}
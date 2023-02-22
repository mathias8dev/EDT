package com.mathias8dev.edt.domain.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mathias8dev.edt.domain.persistence.model.Edt
import com.mathias8dev.edt.domain.persistence.model.EdtAndTask
import com.mathias8dev.edt.domain.persistence.repository.EdtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EdtViewModel @Inject constructor(private val edtRepository: EdtRepository) : ViewModel() {

    val allEdts: LiveData<List<Edt>> = edtRepository.allEdts
    val foundEdt: LiveData<List<Edt>> = edtRepository.foundEdt
    val foundEdtAndTasks: LiveData<List<EdtAndTask>> = edtRepository.foundEdtAndTasks


    fun getAllEdts() {
        edtRepository.getAllEdts()
    }


    fun findEdtByDayNameAndWeekName(dayName: String, weekName: String) {
        edtRepository.findEdtByDayNameAndWeekName(dayName, weekName)
    }

    fun getAllEdtsWithTasksByDayNameAndWeekName(dayName: String, weekName: String) {
        edtRepository.getAllEdtsWithTasksByDayNameAndWeekName(dayName, weekName)
    }

    fun insertEdt(newEdt: Edt) {
        edtRepository.insertEdt(newEdt)
    }

    fun deleteEdt(edt: Edt) {
        edtRepository.deleteEdt(edt)
    }

    fun updateEdt(edt: Edt) {
        edtRepository.updateEdt(edt)
    }


}
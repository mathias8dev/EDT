package com.mathias8dev.edt.domain.data

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import com.mathias8dev.edt.domain.persistence.model.EdtAndTask

data class EdtItem(
    val id: Long,
    var startHour: Int,
    var startMinute: Int,
    var endHour: Int,
    var endMinute: Int,
    var edtTitle: String,
    var edtDescription: String,
    var edtTasks: List<EdtItemTask> = emptyList(),
    var color: Long = 0xFF0F9D58
) {
    companion object {
        fun fromEdtAndTask(edtAndTask: EdtAndTask): EdtItem = EdtItem(
            edtAndTask.edt.id,
            edtAndTask.edt.startingTime.hourOfDay,
            edtAndTask.edt.startingTime.minuteOfDay,
            edtAndTask.edt.endingTime.hourOfDay,
            edtAndTask.edt.endingTime.minuteOfDay,
            edtAndTask.edt.title,
            edtAndTask.edt.description,
            edtAndTask.tasks.map { EdtItemTask.fromTask(it) },
            color = edtAndTask.edt.color
        )

    }
}
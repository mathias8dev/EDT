package com.mathias8dev.edt.domain.data

import com.mathias8dev.edt.domain.persistence.model.Task

data class EdtItemTask(
    val id: Long,
    val taskTitle: String,
    val isDone: Boolean,
) {
    companion object {
        fun fromTask(task: Task): EdtItemTask = EdtItemTask(
            task.id,
            task.title,
            task.isTerminated
        )
    }
}


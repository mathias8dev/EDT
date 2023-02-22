package com.mathias8dev.edt.domain.data

import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import com.mathias8dev.edt.domain.persistence.model.Task
import org.joda.time.DateTime


data class TaskItemState(
    val taskId: Long,
    val taskTitle: String,
    val isTerminated: Boolean,
    val taskCategory: String?,
    val taskEndingDateTime: DateTime,
    var color: Long = 0xFF0F9D58
) {
    companion object {
        fun newFromTask(it: Task, taskCategory: String? = null): TaskItemState = TaskItemState(
            it.id,
            it.title,
            it.isTerminated,
            taskCategory,
            it.endingDateTime,
            it.color
        )

    }
}
package com.mathias8dev.edt.domain.persistence.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndTask(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val tasks: List<Task>
)

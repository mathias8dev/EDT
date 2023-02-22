package com.mathias8dev.edt.domain.persistence.model

import androidx.annotation.NonNull
import androidx.room.*
import com.mathias8dev.edt.domain.persistence.DateTimeTypeConverter
import org.joda.time.DateTime


@Entity(tableName = "edts", indices = [Index(value = ["day_name", "week_name"])])
data class Edt(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var title: String,
    @ColumnInfo(name = "starting_time")
    var startingTime: DateTime,
    @ColumnInfo(name = "ending_time")
    var endingTime: DateTime,
    var description: String,
    @ColumnInfo(name = "day_name")
    var dayName: String,
    @ColumnInfo(name = "week_name")
    var weekName: String,
    var color: Long
)

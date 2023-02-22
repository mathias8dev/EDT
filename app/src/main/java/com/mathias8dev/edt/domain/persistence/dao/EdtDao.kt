package com.mathias8dev.edt.domain.persistence.dao

import androidx.room.*
import com.mathias8dev.edt.domain.persistence.model.Edt
import com.mathias8dev.edt.domain.persistence.model.Task


@Dao
interface EdtDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEdt(edt: Edt)

    @Update
    fun updateEdt(edt: Edt)

    @Delete
    fun deleteEdt(edt: Edt)

    @Query("SELECT * FROM edts")
    fun getAllEdts(): List<Edt>

    @Query("SELECT * FROM edts ORDER BY starting_time ASC")
    fun getAllEdtsOrderByStartingTime(): List<Edt>

    @Query("SELECT * FROM edts WHERE day_name = :dayName AND week_name = :weekName ORDER BY starting_time ASC")
    fun getAllEdtsByDayNameAndWeekNameOrderByStartingTime(dayName: String, weekName: String): List<Edt>

}
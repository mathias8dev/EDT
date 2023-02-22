package com.mathias8dev.edt.domain.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mathias8dev.edt.domain.persistence.DateTimeTypeConverter
import com.mathias8dev.edt.domain.persistence.dao.CategoryDao
import com.mathias8dev.edt.domain.persistence.dao.EdtDao
import com.mathias8dev.edt.domain.persistence.dao.TaskDao
import com.mathias8dev.edt.domain.persistence.model.Category
import com.mathias8dev.edt.domain.persistence.model.Edt
import com.mathias8dev.edt.domain.persistence.model.Task

@Database(entities = [Edt::class, Task::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeTypeConverter::class)
abstract class EdtRoomDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun taskDao(): TaskDao
    abstract fun edtDao(): EdtDao

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: EdtRoomDatabase? = null

        fun getInstance(context: Context): EdtRoomDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        EdtRoomDatabase::class.java,
                        "edt_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
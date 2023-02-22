package com.mathias8dev.edt.domain.hilt

import android.content.Context
import com.mathias8dev.edt.domain.persistence.dao.CategoryDao
import com.mathias8dev.edt.domain.persistence.dao.EdtDao
import com.mathias8dev.edt.domain.persistence.dao.TaskDao
import com.mathias8dev.edt.domain.persistence.db.EdtRoomDatabase
import com.mathias8dev.edt.domain.persistence.db.EdtRoomDatabase_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideCategoryDao(appDatabase: EdtRoomDatabase): CategoryDao = appDatabase.categoryDao()
    @Provides
    fun provideEdtDao(appDatabase: EdtRoomDatabase): EdtDao = appDatabase.edtDao()
    @Provides
    fun provideTaskDao(appDatabase: EdtRoomDatabase): TaskDao = appDatabase.taskDao()

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): EdtRoomDatabase {
        return EdtRoomDatabase.getInstance(appContext)
    }
}
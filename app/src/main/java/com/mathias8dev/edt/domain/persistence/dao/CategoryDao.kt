package com.mathias8dev.edt.domain.persistence.dao

import androidx.room.*
import com.mathias8dev.edt.domain.persistence.model.Category
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask


@Dao
interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM categories")
    fun getCategoryWithTasks(): List<CategoryAndTask>

    @Transaction
    @Query("SELECT * FROM categories WHERE title LIKE :categoryTitle")
    fun getCategoryWithTasksByCategoryTitle(categoryTitle: String): List<CategoryAndTask>

    @Query("SELECT * FROM categories")
    fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun findById(categoryId: Long): Category?
}
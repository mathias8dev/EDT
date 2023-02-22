package com.mathias8dev.edt.domain.persistence.repository

import androidx.lifecycle.MutableLiveData
import com.mathias8dev.edt.domain.persistence.dao.CategoryDao
import com.mathias8dev.edt.domain.persistence.model.Category
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import kotlinx.coroutines.*
import javax.inject.Inject


class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

    val allCategories = MutableLiveData<List<Category>>()
    val allCategoriesWithTask = MutableLiveData<List<CategoryAndTask>>()
    val allCategoriesWithTaskByCategoryTitle = MutableLiveData<List<CategoryAndTask>>()
    val foundCategoryById = MutableLiveData<Category?>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertCategory(newCategory: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.insertCategory(newCategory)
        }
    }

    fun deleteCategory(category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.deleteCategory(category)
        }
    }

    fun updateCategory(category: Category) {
        coroutineScope.launch(Dispatchers.IO) {
            categoryDao.updateCategory(category)
        }
    }


    fun getAllCategories() {
        coroutineScope.launch(Dispatchers.IO) {
            allCategories.value = categoryDao.getCategories()
        }
    }

    fun getAllCategoriesWithTasks() {
        coroutineScope.launch(Dispatchers.IO) {
            allCategoriesWithTask.value = categoryDao.getCategoryWithTasks()
        }
    }

    fun getCategoryWithTasksByCategoryTitle(categoryTitle: String) {
        coroutineScope.launch(Dispatchers.IO) {
            allCategoriesWithTaskByCategoryTitle.value = asyncFind(categoryTitle).await()
        }
    }

    private fun asyncFind(categoryTitle: String): Deferred<List<CategoryAndTask>> = coroutineScope.async {
        return@async categoryDao.getCategoryWithTasksByCategoryTitle(categoryTitle)
    }

    fun findById(categoryId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
             foundCategoryById.value = categoryDao.findById(categoryId)
        }

    }

}
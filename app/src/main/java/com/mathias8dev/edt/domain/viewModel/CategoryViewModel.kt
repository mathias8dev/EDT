package com.mathias8dev.edt.domain.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathias8dev.edt.domain.persistence.model.Category
import com.mathias8dev.edt.domain.persistence.model.CategoryAndTask
import com.mathias8dev.edt.domain.persistence.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) : ViewModel() {

    val allCategories: LiveData<List<Category>> = categoryRepository.allCategories
    val allCategoriesWithTask: LiveData<List<CategoryAndTask>> = categoryRepository.allCategoriesWithTask
    val allCategoriesWithTaskByCategoryTitle: LiveData<List<CategoryAndTask>> = categoryRepository.allCategoriesWithTaskByCategoryTitle
    var currentSelectedCategory: MutableState<Category?> = mutableStateOf(null)
    var foundCategoryById: LiveData<Category?> = categoryRepository.foundCategoryById

    fun getAllCategories() {

        categoryRepository.getAllCategories()
    }

    fun getAllCategoriesWithTasks() {
        categoryRepository.getAllCategoriesWithTasks()
    }

    fun getCategoryWithTasksByCategoryTitle(categoryTitle: String) {
        categoryRepository.getCategoryWithTasksByCategoryTitle(categoryTitle)
    }

    fun insertCategory(newCategory: Category) {
        categoryRepository.insertCategory(newCategory)
    }

    fun findById(categoryId: Long) {
        categoryRepository.findById(categoryId)
    }

    fun deleteCategory(category: Category) {
        categoryRepository.deleteCategory(category)
    }

    fun updateCategory(category: Category) {
        categoryRepository.updateCategory(category)
    }

    fun selectCategory(category: Category?) {
        currentSelectedCategory.value = category
    }
}
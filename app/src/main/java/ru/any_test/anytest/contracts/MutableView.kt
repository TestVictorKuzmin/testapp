package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category

interface MutableView {
    fun onCategoryViewCreated(position: Int, title: String)

    fun showCategories(categories: List<Category>)

    fun addCategory(category: Category)
}
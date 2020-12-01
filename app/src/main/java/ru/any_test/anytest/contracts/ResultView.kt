package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category

interface ResultView {
    fun onCategoryViewCreated(categories: List<Category>)

    fun showCategories(categories: List<Category>)
}
package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category

interface ResultRepository {
    fun getCategories(result: List<Category>)

    fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener)

    fun getCategories(): List<Category>

}
package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category
import java.io.Serializable

interface OnCategoriesFetchedListener : Serializable {
    fun showCategories(categories: List<Category>)

    fun error(message: String)
}
package ru.any_test.anytest.contracts

import android.view.View
import ru.any_test.anytest.model.Category

interface ImmutableView {
    fun onCategoryViewCreated(position: Int)

    fun showCategories(categories: List<Category>)

    fun showProgress()

    fun hideProgress()

    fun updateCategories(categories: List<Category>)
}
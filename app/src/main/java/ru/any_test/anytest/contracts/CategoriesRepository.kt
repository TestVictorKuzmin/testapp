package ru.any_test.anytest.contracts

import io.reactivex.Single
import ru.any_test.anytest.model.Category

interface CategoriesRepository<T> {
//    fun getCategories(position: Int) //пока старое заменили на новое с дженерик параметром.

    fun getCategories(position: Int) : T

    fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener)

    fun getCategories(): List<Category>
}
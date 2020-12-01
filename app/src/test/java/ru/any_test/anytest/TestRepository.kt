package ru.any_test.anytest

import io.reactivex.Single
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.Category

open class TestRepository : CategoriesRepository<Single<List<Category>>>{

    override fun getCategories(position: Int): Single<List<Category>> {
        val categories: List<Category> = mutableListOf<Category>(
            Category.TestCategory(
                0,
                "Тесты охранников",
                null,
                "Тесты для охранников разных разрядов",
                10,
                null
            )
        )

        val categorySingle = Single.fromCallable {
            categories
        }
        return categorySingle
    }

    override fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener) {
        TODO("Not yet implemented")
    }

    override fun getCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}
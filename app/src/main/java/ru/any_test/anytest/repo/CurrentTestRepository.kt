package ru.any_test.anytest.repo

import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.Category

class CurrentTestRepository {

    var testList: Int = 0

    var testQuestion: List<Category> = mutableListOf()

}
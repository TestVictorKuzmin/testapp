package ru.any_test.anytest.repo

import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.contracts.ResultRepository
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.test.GenCategory

class AnswersRepositoryImpl : ResultRepository {

    var answersForTest: List<Category> = ArrayList()

    lateinit var onQuestionsFetchedListener: OnCategoriesFetchedListener

    override fun getCategories(result: List<Category>) {
        answersForTest = result
        onQuestionsFetchedListener.showCategories(answersForTest)
    }

    override fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener) {
        this.onQuestionsFetchedListener = onCategoriesFetchedListener
    }

    override fun getCategories(): List<Category> {
        onQuestionsFetchedListener.showCategories(answersForTest)
        return answersForTest
    }
}
package ru.any_test.anytest.repo

import io.reactivex.Single
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.utils.Network
import ru.any_test.anytest.repo.responses.TestResponse

class TestRepositoryImpl : CategoriesRepository<Single<TestResponse>> {

    lateinit var onCommentsFetchedListener: OnCategoriesFetchedListener
    var testId = -1
    private val apiService = Network.create()
    private var testQuestions = mutableListOf<Category>()
    lateinit var testResponse: Single<TestResponse>

    override fun getCategories(position: Int) : Single<TestResponse> {
        if(position == QUESTIONS_LOADED) {
            return testResponse
        }
        else if(testId != position) {
            testId = position
            testResponse = apiService.getTest(testId)
            return testResponse
        }
        else {
            return testResponse
        }
    }

    override fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener) {
        this.onCommentsFetchedListener = onCategoriesFetchedListener
    }

    override fun getCategories(): List<Category> {
        return testQuestions
    }

    companion object {
        const val QUESTIONS_LOADED = -1
    }
}
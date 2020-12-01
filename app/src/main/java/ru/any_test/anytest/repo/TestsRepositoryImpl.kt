package ru.any_test.anytest.repo

import io.reactivex.Single
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.*
import ru.any_test.anytest.repo.utils.Network
import ru.any_test.anytest.repo.responses.TestListResponse

class TestsRepositoryImpl : CategoriesRepository<Single<List<Category>>> {
    var testPosition = -1
    lateinit var onTestsFetchedListener: OnCategoriesFetchedListener
    private val apiService = Network.create()
    private var tests = mutableListOf<Category>()

    override fun getCategories(position: Int): Single<List<Category>> {
        if(testPosition != position + 1) { //клик по элементам начинается с 0. А тесты с 1.
            testPosition = position + 1
            tests = mutableListOf()
            return apiService.getTestList(testPosition).map { convertResponse(it)}
        }
        else {
            return Single.just(tests)
        }
    }

    /**
     * Конвертируем десериализованный объект-ответ в List<Category>.
     */
    private fun convertResponse(responseList: TestListResponse): List<Category> {
        try {
            responseList.tests.forEach{
                tests.add(Category.Test(
                    it.id,
                    it.title,
                    null,
                    null,
                    it.viewCount,
                    it.successCount
                )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return tests
    }

    override fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener) {
        this.onTestsFetchedListener = onCategoriesFetchedListener
    }

    override fun getCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}
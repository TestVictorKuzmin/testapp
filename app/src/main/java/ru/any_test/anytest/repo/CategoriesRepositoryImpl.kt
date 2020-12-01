package ru.any_test.anytest.repo

import io.reactivex.Single
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.*
import ru.any_test.anytest.repo.responses.CategoryJson
import ru.any_test.anytest.repo.utils.Network

class CategoriesRepositoryImpl : CategoriesRepository<Single<List<Category>>> {

    private val apiService = Network.create()
    private var totalPages = -1
    private var currentPage = -1

    lateinit var onCategoriesFetchedListener: OnCategoriesFetchedListener
    private var categories = mutableListOf<Category>()

    override fun getCategories(position: Int): Single<List<Category>> {
        if(currentPage < 0) {
            return apiService.getCategories(0).map {
                totalPages = it.totalPages
                currentPage = it.currentPage
                convertResponse(it.categories)}
        }
        else {
            if(currentPage < totalPages - 1) {
                return apiService.getCategories(currentPage + 1).map {
                    totalPages = it.totalPages
                    currentPage = it.currentPage
                    convertResponse(it.categories)}
            }
            else {
                return Single.just(categories)
            }
        }
    }

    /**
     * Конвертируем десериализованный объект-ответ в List<Category>.
     * TODO нужно этот метод вынести в use case!
     */
    private fun convertResponse(responseList: List<CategoryJson>): List<Category> {
        try {
            responseList.forEach{
                categories.add(Category.TestCategory(
                    it.categoryId,
                    it.title,
                    it.image,
                    it.title,
                    it.testsCount,
                    null
                )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return categories
    }

    override fun attach(onCategoriesFetchedListener: OnCategoriesFetchedListener) {
        this.onCategoriesFetchedListener = onCategoriesFetchedListener
    }

    override fun getCategories(): List<Category> {
        return categories
    }
}
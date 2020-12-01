package ru.any_test.anytest

import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import ru.any_test.anytest.domain.executor.JobExecutor
import ru.any_test.anytest.domain.usecases.LoadCategoriesUseCase
import ru.any_test.anytest.model.Category

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitQuestion {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    @Throws(Exception::class)
    fun testCategoryFound() {
        val categoryRepository = Mockito.mock(TestRepository::class.java)

        //expected
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

        Mockito.`when`(categoryRepository?.getCategories(0)).thenReturn(categorySingle)

        val loadingCategoryUseCase: LoadCategoriesUseCase = LoadCategoriesUseCase(
            JobExecutor(),
            TestThread(),
            categoryRepository!!
        )
        loadingCategoryUseCase.execute()

        Mockito.verify<Any>(categoryRepository.getCategories(0))
    }

}

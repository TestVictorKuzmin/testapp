package ru.any_test.anytest.testlist

import io.reactivex.observers.DisposableSingleObserver
import ru.any_test.anytest.comments.FragmentComments
import ru.any_test.anytest.contracts.*
import ru.any_test.anytest.domain.usecases.LoadCategoriesUseCase
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.CurrentTestRepository
import ru.any_test.anytest.root.MainActivity

class TestsPresenterImpl(
    private val fragmentTestsList: ImmutableView,
    private val fragmentComments: MutableView,
    private val loadContentUseCase: LoadCategoriesUseCase,
    private val currentTestRepository: CurrentTestRepository
) :
    CategoriesPresenter{

    lateinit var tests: List<Category>
    private var categoryId: Int = -1

    override fun onPresenterCreated(position: Int) {
        if(position < 0) {
            categoryId = currentTestRepository.testList
        }
        else {
            categoryId = position
            currentTestRepository.testList = categoryId
        }
        fragmentTestsList.showProgress()
        loadContentUseCase.setParam(categoryId)
        loadContentUseCase.execute(TestsObserver())
    }

    override fun onDataRequired() {
        TODO("Not yet implemented")
    }

    /**
     * По номеру позиции берем id теста. Чтобы по этому id уже найти для него пул вопросов.
     */
    override fun onItemWasClicked(position: Int) {
        val id = (tests.get(position) as Category.Test).id
        val title = tests.get(position).name
        fragmentComments.onCategoryViewCreated(id, title)
        ((fragmentTestsList as FragmentTestsList)
            .activity as RootView)
            .replaceFragment(fragmentComments as FragmentComments, MainActivity.VIEW_TESTS_LIST, true)
    }

    private inner class TestsObserver : DisposableSingleObserver<List<Category>>() {
        override fun onSuccess(categories: List<Category>) {
            tests = categories
            fragmentTestsList.showCategories(categories)
            fragmentTestsList.hideProgress()
        }

        override fun onError(e: Throwable) {
            //TODO Пока никак ошибку не обрабтываем
            loadContentUseCase.setParam(categoryId)
            loadContentUseCase.execute(TestsObserver())
        }
    }
}
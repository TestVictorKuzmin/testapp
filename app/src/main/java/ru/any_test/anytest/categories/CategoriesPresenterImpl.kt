package ru.any_test.anytest.categories

import android.util.Log
import io.reactivex.observers.DisposableSingleObserver
import ru.any_test.anytest.contracts.CategoriesPresenter
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.contracts.RootView
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.domain.usecases.LoadCategoriesUseCase
import ru.any_test.anytest.testlist.FragmentTestsList
import ru.any_test.anytest.root.MainActivity

class CategoriesPresenterImpl(
    private val fragmentCategories: ImmutableView,
    private val fragmentTestsList: ImmutableView,
    private val loadContentUseCase: LoadCategoriesUseCase
) :
    CategoriesPresenter{

    private var categoryId: Int = -1

    override fun onPresenterCreated(position: Int) {
        categoryId = position  //TODO Этот id позиции нужно будет проверить и убрать! Здесь он не нужен.
        fragmentCategories.showProgress()
        loadContentUseCase.execute(CategoriesObserver(true))
    }

    override fun onDataRequired() {
        fragmentCategories.showProgress()
        loadContentUseCase.execute(CategoriesObserver(false))
    }

    override fun onItemWasClicked(position: Int) {
        fragmentTestsList.onCategoryViewCreated(position)
        ((fragmentCategories as FragmentCategoriesList)
            .activity as RootView)
            .replaceFragment(fragmentTestsList as FragmentTestsList, MainActivity.VIEW_PAGER, true)
    }

    private inner class CategoriesObserver(private val initialLoad: Boolean) : DisposableSingleObserver<List<Category>>() {

        override fun onSuccess(categories: List<Category>) {
            if(initialLoad) {
                fragmentCategories.showCategories(categories)
                fragmentCategories.hideProgress()
            }
            else {
                fragmentCategories.updateCategories(categories)
                fragmentCategories.hideProgress()
            }
        }

        override fun onError(e: Throwable) {
            loadContentUseCase.execute(CategoriesObserver(initialLoad))
        }
    }
}
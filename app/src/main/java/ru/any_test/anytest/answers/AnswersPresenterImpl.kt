package ru.any_test.anytest.answers

import ru.any_test.anytest.contracts.*
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.model.Category

class AnswersPresenterImpl(
    private val fragmentAnswerList: ResultView,
    private val answersRepository: ResultRepository
) :
    ResultPresenter, OnCategoriesFetchedListener {

    init {
        answersRepository.attach(this)
    }

    override fun onPresenterCreated(list: List<Category>?) {
        if(list == null) {
            answersRepository.getCategories()
        }
        else {
            answersRepository.getCategories(list)
        }
    }

    override fun showCategories(categories: List<Category>) {
        fragmentAnswerList.showCategories(categories)
    }

    override fun error(message: String) {
        TODO("Not yet implemented")
    }

    override fun onConfirm(list: List<Category>) {
        TODO("Not yet implemented")
    }
}
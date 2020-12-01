package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category

interface ResultPresenter {
    fun onPresenterCreated(list: List<Category>?)

    fun onConfirm(list: List<Category>)
}
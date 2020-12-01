package ru.any_test.anytest.contracts

import ru.any_test.anytest.model.Category

interface TestPresenter {
    fun onPresenterCreated(position: Int)

    fun onConfirm(position: Int, list: List<String>?)
}
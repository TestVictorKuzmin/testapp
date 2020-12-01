package ru.any_test.anytest.contracts

import android.view.View

interface CategoriesPresenter {
    fun onPresenterCreated(position: Int)

    fun onDataRequired()

    fun onItemWasClicked(position: Int)
}
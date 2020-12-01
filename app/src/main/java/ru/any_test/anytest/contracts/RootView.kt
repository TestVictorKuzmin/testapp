package ru.any_test.anytest.contracts

import androidx.fragment.app.Fragment
import ru.any_test.anytest.R

interface RootView {
    fun replaceFragment(fragment: Fragment, tag: String, inBackStack: Boolean)
}
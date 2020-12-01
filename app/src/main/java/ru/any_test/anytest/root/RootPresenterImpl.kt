package ru.any_test.anytest.root

import androidx.fragment.app.Fragment
import ru.any_test.anytest.contracts.RootPresenter
import ru.any_test.anytest.contracts.RootView
import ru.any_test.anytest.root.MainActivity

class RootPresenterImpl(val fragmentPager: Fragment) : RootPresenter {
        override fun onViewCreated(rootView: RootView) {
        (rootView as MainActivity).replaceFragment(fragmentPager, MainActivity.VIEW_PAGER, false)
    }
}
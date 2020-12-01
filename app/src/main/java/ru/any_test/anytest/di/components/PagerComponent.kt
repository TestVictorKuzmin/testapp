package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.di.modules.PagerModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.root.FragmentBlog
import ru.any_test.anytest.root.FragmentPager

@ContractScope
@Subcomponent(modules = [PagerModule::class])
interface PagerComponent {

    fun inject(fragmentPager: FragmentPager)

    fun inject(fragmentBlog: FragmentBlog)
}
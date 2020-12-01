package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.di.modules.CategoriesModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.categories.FragmentCategoriesList

@ContractScope
@Subcomponent(modules = [CategoriesModule::class])
interface CategoriesComponent {

    fun inject(fragmentCategoriesList: FragmentCategoriesList)
}
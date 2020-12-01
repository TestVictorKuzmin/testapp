package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.di.modules.TestsModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.testlist.FragmentTestsList

@ContractScope
@Subcomponent(modules = [TestsModule::class])
interface TestsComponent {

    fun inject(fragmentTestsList: FragmentTestsList)

}
package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.comments.FragmentComments
import ru.any_test.anytest.di.modules.CommentsModule
import ru.any_test.anytest.di.modules.TestsModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.testlist.FragmentTestsList

@ContractScope
@Subcomponent(modules = [CommentsModule::class])
interface CommentsComponent {

    fun inject(fragmentComments: FragmentComments)

}
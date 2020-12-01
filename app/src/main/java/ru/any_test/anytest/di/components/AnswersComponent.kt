package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.di.modules.AnswersModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.answers.FragmentAnswerList

@ContractScope
@Subcomponent(modules = [AnswersModule::class])
interface AnswersComponent {

    fun inject(fragmentAnswerList: FragmentAnswerList)
}
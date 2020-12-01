package ru.any_test.anytest.di.components

import dagger.Subcomponent
import ru.any_test.anytest.di.modules.QuestionModule
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.question.FragmentQuestion

@ContractScope
@Subcomponent(modules = [QuestionModule::class])
interface QuestionComponent {

    fun inject(fragmentQuestion: FragmentQuestion)
}
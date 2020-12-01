package ru.any_test.anytest.di.modules

import dagger.Module
import dagger.Provides
import ru.any_test.anytest.answers.AnswersAdapter
import ru.any_test.anytest.answers.AnswersPresenterImpl
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.answers.FragmentAnswerList
import ru.any_test.anytest.contracts.ResultPresenter
import ru.any_test.anytest.contracts.ResultRepository
import javax.inject.Named

@Module
class AnswersModule(val fragmentAnswerList: FragmentAnswerList) {

    @Named("answersAdapter")
    @Provides
    fun provideAnswersAdapter(): AnswersAdapter = AnswersAdapter(fragmentAnswerList.requireActivity())

    @ContractScope
    @Named("answersPresenter")
    @Provides
    fun provideAnswersPresenter(
        @Named("answersRepository")answersRepository: ResultRepository
    ): ResultPresenter {
        return AnswersPresenterImpl(fragmentAnswerList, answersRepository)
    }
}
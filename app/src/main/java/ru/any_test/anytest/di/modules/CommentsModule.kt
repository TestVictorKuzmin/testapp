package ru.any_test.anytest.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import ru.any_test.anytest.UIThread
import ru.any_test.anytest.comments.CommentsAdapter
import ru.any_test.anytest.comments.CommentsPresenterImpl
import ru.any_test.anytest.comments.FragmentComments
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.contracts.TestPresenter
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.domain.executor.JobExecutor
import ru.any_test.anytest.domain.usecases.LoadTestUseCase
import ru.any_test.anytest.utils.Images
import ru.any_test.anytest.question.FragmentQuestion
import ru.any_test.anytest.repo.responses.TestResponse
import ru.any_test.anytest.root.MainActivity
import javax.inject.Named

@Module
class CommentsModule(val mainActivity: MainActivity, val fragmentComments: FragmentComments) {

    @ContractScope
    @Named("fragmentTest")
    @Provides
    fun provideFragmentTest(): ImmutableView = FragmentQuestion.newInstance()

    @Named("commentsAdapter")
    @Provides
    fun provideCommentsAdapter(images: Images): CommentsAdapter =
        CommentsAdapter(images)

    @ContractScope
    @Named("commentsPresenter")
    @Provides
    fun provideCommentsPresenter(
        @Named("fragmentTest")testFragment: ImmutableView,
        loadTestUseCase: LoadTestUseCase
    ): TestPresenter {
        return CommentsPresenterImpl(fragmentComments, testFragment, loadTestUseCase)
    }

    @ContractScope
    @Provides
    fun provideLoadingTestUseCase(
        @Named("testRepository") commentsRepository: CategoriesRepository<Single<TestResponse>>
    ): LoadTestUseCase {
        return LoadTestUseCase(
            JobExecutor(),
            UIThread(),
            commentsRepository
        )
    }
}
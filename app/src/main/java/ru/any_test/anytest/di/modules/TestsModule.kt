package ru.any_test.anytest.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import ru.any_test.anytest.UIThread
import ru.any_test.anytest.comments.FragmentComments
import ru.any_test.anytest.testlist.TestsAdapter
import ru.any_test.anytest.contracts.CategoriesPresenter
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.MutableView
import ru.any_test.anytest.testlist.TestsPresenterImpl
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.domain.executor.JobExecutor
import ru.any_test.anytest.domain.usecases.LoadCategoriesUseCase
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.CurrentTestRepository
import ru.any_test.anytest.utils.Images
import ru.any_test.anytest.testlist.FragmentTestsList
import ru.any_test.anytest.root.MainActivity
import javax.inject.Named

@Module
class TestsModule(val mainActivity: MainActivity, val fragmentTestsList: FragmentTestsList) {

    @ContractScope
    @Named("fragmentComments")
    @Provides
    fun provideFragmentComments(): MutableView = FragmentComments.newInstance()

    @Named("testsAdapter")
    @Provides
    fun provideTestsAdapter(images: Images): TestsAdapter =
        TestsAdapter(images)

    @ContractScope
    @Named("testsPresenter")
    @Provides
    fun provideTestsPresenter(
        @Named("fragmentComments")commentsFragment: MutableView,
        loadCategoriesUseCase: LoadCategoriesUseCase,
        currentTestRepository: CurrentTestRepository
    ): CategoriesPresenter {
        return TestsPresenterImpl(fragmentTestsList, commentsFragment, loadCategoriesUseCase, currentTestRepository)
    }

    @ContractScope
    @Provides
    fun provideLoadingCategoriesUseCase(
        @Named("testListRepository") testsRepository: CategoriesRepository<Single<List<Category>>>
    ): LoadCategoriesUseCase {
        return LoadCategoriesUseCase(
            JobExecutor(),
            UIThread(),
            testsRepository
        )
    }
}
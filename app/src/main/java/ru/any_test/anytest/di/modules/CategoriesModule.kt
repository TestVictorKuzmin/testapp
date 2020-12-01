package ru.any_test.anytest.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import ru.any_test.anytest.UIThread
import ru.any_test.anytest.categories.CategoriesAdapter
import ru.any_test.anytest.contracts.CategoriesPresenter
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.categories.CategoriesPresenterImpl
import ru.any_test.anytest.di.scopes.ContractScope
import ru.any_test.anytest.utils.Images
import ru.any_test.anytest.categories.FragmentCategoriesList
import ru.any_test.anytest.domain.executor.JobExecutor
import ru.any_test.anytest.domain.usecases.LoadCategoriesUseCase
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.testlist.FragmentTestsList
import javax.inject.Named

@Module
class CategoriesModule (val fragmentCategoriesList: FragmentCategoriesList) {

    @ContractScope
    @Named("fragmentTestsList")
    @Provides
    fun provideFragmentTests(): ImmutableView = FragmentTestsList.newInstance()

    @Named("categoriesAdapter")
    @Provides
    fun provideCategoriesAdapter(images: Images): CategoriesAdapter = CategoriesAdapter(images)

    @ContractScope
    @Named("categoriesPresenter")
    @Provides
    fun provideCategoriesPresenter(
        @Named("fragmentTestsList") fragmentTestsList: ImmutableView,
        loadCategoriesUseCase: LoadCategoriesUseCase
        ): CategoriesPresenter {
        return CategoriesPresenterImpl(fragmentCategoriesList, fragmentTestsList, loadCategoriesUseCase)
    }

    @ContractScope
    @Provides
    fun provideLoadingCategoriesUseCase(
        @Named("categoriesRepository") categoriesRepository: CategoriesRepository<Single<List<Category>>>
    ): LoadCategoriesUseCase {
        return LoadCategoriesUseCase(
            JobExecutor(),
            UIThread(),
            categoriesRepository
        )
    }
}
package ru.any_test.anytest.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Single
import ru.any_test.anytest.repo.AnswersRepositoryImpl
import ru.any_test.anytest.repo.CategoriesRepositoryImpl
import ru.any_test.anytest.repo.TestRepositoryImpl
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.contracts.ResultRepository
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.CurrentTestRepository
import ru.any_test.anytest.repo.responses.TestResponse
import ru.any_test.anytest.repo.TestsRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Named("categoriesRepository")
    @Provides
    fun provideCategoriesRepository(): CategoriesRepository<Single<List<Category>>> {
        return CategoriesRepositoryImpl()
    }

    @Singleton
    @Named("answersRepository")
    @Provides
    fun provideAnswersRepository(): ResultRepository {
        return AnswersRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideCurrentTestRepository(): CurrentTestRepository {
        return CurrentTestRepository()
    }

    @Singleton
    @Named("testListRepository")
    @Provides
    fun provideTestsRepository(): CategoriesRepository<Single<List<Category>>> {
        return TestsRepositoryImpl()
    }

    @Singleton
    @Named("testRepository")
    @Provides
    fun provideCommentsRepository(): CategoriesRepository<Single<TestResponse>> {
        return TestRepositoryImpl()
    }

}
package ru.any_test.anytest.domain.usecases

import io.reactivex.Single
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.domain.executor.PostExecutionThread
import ru.any_test.anytest.domain.executor.ThreadExecutor
import ru.any_test.anytest.model.Category
import javax.inject.Inject

class LoadCategoriesUseCase
@Inject
constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val categoriesRepository: CategoriesRepository<Single<List<Category>>>
) : SingleUseCase<List<Category>, Unit>(threadExecutor, postExecutionThread) {

    private var param = 0

    override fun buildUseCaseSingle(params: Unit?): Single<List<Category>> {
        return categoriesRepository.getCategories(param)
    }

    fun setParam(param: Int) {
        this.param = param
    }

}
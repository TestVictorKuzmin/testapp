package ru.any_test.anytest.domain.usecases

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.any_test.anytest.domain.executor.PostExecutionThread
import ru.any_test.anytest.domain.executor.ThreadExecutor
import io.reactivex.schedulers.Schedulers

/**
 * It contains the Schedulers to use for the created Observables/Singles/Completables and
 * some methods for disposing a use case. This base class is used by all reactive use cases.
 */
abstract class BaseUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) {

    protected val threadExecutorScheduler: Scheduler = Schedulers.from(threadExecutor)

    protected val postExecutionThreadScheduler: Scheduler = postExecutionThread.scheduler

    private val disposables = CompositeDisposable()

    open fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(checkNotNull(disposable, { "disposable cannot be null!" }))
    }
}
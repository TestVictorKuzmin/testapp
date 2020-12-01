package ru.any_test.anytest.domain.executor

import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobExecutor
@Inject
constructor() : ThreadExecutor {

    companion object {
        private const val THREAD_POOL = 3
    }

    private val threadPoolExecutor: Executor = Executors.newFixedThreadPool(THREAD_POOL)

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }
}

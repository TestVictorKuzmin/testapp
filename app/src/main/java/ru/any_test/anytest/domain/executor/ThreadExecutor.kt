package ru.any_test.anytest.domain.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * [ru.any_test.anytest.domain.usecases.BaseReactiveUseCase] out of the UI thread.
 */
interface ThreadExecutor : Executor {

}
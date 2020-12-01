package ru.any_test.anytest

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.any_test.anytest.domain.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class provides main UI thread.
 */
@Singleton
class UIThread
@Inject
constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}

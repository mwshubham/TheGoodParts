package app.thegoodparts.threading

import android.os.Process
import app.thegoodparts.constants.CoreLoggingConstants
import timber.log.Timber
import java.util.concurrent.*

/*
* Singleton class for default executor supplier
*
* Reference: https://blog.mindorks.com/threadpoolexecutor-in-android-8e9d22330ee3
*/
class DefaultExecutorSupplier
private constructor() {

    private val backgroundThreadPoolExecutor: ThreadPoolExecutor

    private val lightWeightBackgroundThreadPoolExecutor: ThreadPoolExecutor

    private val mainThreadPoolExecutor: Executor

    init {
        val backgroundPriorityThreadFactory: ThreadFactory =
            PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND)

        backgroundThreadPoolExecutor = ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            30L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(),
            backgroundPriorityThreadFactory
        )
        lightWeightBackgroundThreadPoolExecutor = ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            30L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(),
            backgroundPriorityThreadFactory
        )
        mainThreadPoolExecutor = MainThreadExecutor()
    }

    fun getBackgroundThreadPoolExecutor(): ThreadPoolExecutor {
        return backgroundThreadPoolExecutor
    }

    fun getLightWeightBackgroundThreadPoolExecutor(): ThreadPoolExecutor {
        return lightWeightBackgroundThreadPoolExecutor
    }

    fun getMainThreadPoolExecutor(): Executor {
        return mainThreadPoolExecutor
    }

    companion object {
        private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

        private var sInstance: DefaultExecutorSupplier? = null

        fun getInstance(): DefaultExecutorSupplier {
            Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
            if (sInstance == null) {
                synchronized(this) {
                    sInstance = DefaultExecutorSupplier()
                }
            }
            return sInstance ?: throw IllegalStateException("Failure to create instance.")
        }
    }
}
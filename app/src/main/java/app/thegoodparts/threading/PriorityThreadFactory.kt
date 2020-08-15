package app.thegoodparts.threading

import android.os.Process
import java.util.concurrent.ThreadFactory

class PriorityThreadFactory(
    private val priority: Int
) : ThreadFactory {
    override fun newThread(runnable: Runnable): Thread {
        val wrapperRunnable = Runnable {
            try {
                Process.setThreadPriority(priority)
            } catch (t: Throwable) {
            }
            runnable.run()
        }
        return Thread(wrapperRunnable)
    }

}
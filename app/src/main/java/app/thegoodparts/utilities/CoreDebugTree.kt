package app.thegoodparts.utilities

import timber.log.Timber

class CoreDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return String.format(
            "[%s][%s():%s][%s]",
            super.createStackElementTag(element),
            element.methodName,
            element.lineNumber,
            Thread.currentThread().name
        )
    }
}
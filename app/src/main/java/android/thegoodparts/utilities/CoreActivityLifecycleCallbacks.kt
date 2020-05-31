package android.thegoodparts.utilities

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CoreActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.i(activity::class.java.simpleName)
        FirebaseCrashlytics.getInstance()
            .setCustomKey(
                "onActivityCreated()",
                activity::class.java.simpleName
            )

        FirebaseCrashlytics.getInstance()
            .log("[CoreActivityLifecycleCallbacks][${activity::class.java.simpleName}][onActivityCreated]")
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.i(activity::class.java.simpleName)
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.i(activity::class.java.simpleName)
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.i(activity::class.java.simpleName)
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.i(activity::class.java.simpleName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.i(activity::class.java.simpleName)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.i(activity::class.java.simpleName)
    }

}
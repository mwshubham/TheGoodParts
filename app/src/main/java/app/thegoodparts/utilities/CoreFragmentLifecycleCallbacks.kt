package app.thegoodparts.utilities

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CoreFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentAttached(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        context: Context
    ) {
        super.onFragmentAttached(fragmentManager, fragment, context)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentCreated(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentCreated(fragmentManager, fragment, savedInstanceState)
        Timber.i(fragment::class.java.simpleName)
        FirebaseCrashlytics.getInstance()
            .setCustomKey(
                "onFragmentCreated()",
                fragment::class.java.simpleName
            )
        FirebaseCrashlytics.getInstance()
            .log("[CoreFragmentLifecycleCallbacks][${fragment::class.java.simpleName}][onFragmentCreated]")
    }

//    override fun onFragmentActivityCreated(
//        fragmentManager: FragmentManager,
//        fragment: Fragment,
//        savedInstanceState: Bundle?
//    ) {
//        super.onFragmentActivityCreated(fragmentManager, fragment, savedInstanceState)
//        Timber.i(fragment::class.java.simpleName)
//    }

    override fun onFragmentStarted(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentStarted(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentResumed(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentResumed(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentPaused(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentPaused(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentStopped(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentStopped(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentViewDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentViewDestroyed(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentDestroyed(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }

    override fun onFragmentDetached(fragmentManager: FragmentManager, fragment: Fragment) {
        super.onFragmentDetached(fragmentManager, fragment)
        Timber.i(fragment::class.java.simpleName)
    }
}
package app.thegoodparts

import android.app.Application
import app.thegoodparts.constants.CoreLoggingConstants
import app.thegoodparts.utilities.CoreActivityLifecycleCallbacks
import app.thegoodparts.utilities.CoreDebugTree
import com.facebook.stetho.Stetho
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()
        initStetho()
        initFirebaseRemoteConfig()
        registerActivityLifecycleCallbacks()
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }

    // ============================================================================================
    //  Private init methods
    // ============================================================================================

    /**
     * Initialize log library Timber only on debug build.
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(CoreDebugTree())
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initFirebaseRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = TimeUnit.HOURS.toSeconds(1)
        }
        Firebase.remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        Firebase.remoteConfig.setConfigSettingsAsync(configSettings)
        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Timber.d("Config params updated: $updated")
                } else {
                    Timber.e("Fetch failed")
                }
            }
    }

    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(CoreActivityLifecycleCallbacks())
    }

}
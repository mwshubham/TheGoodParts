package android.thegoodparts.di.modules

import android.thegoodparts.di.modules.activity.MainActivityModule
import android.thegoodparts.di.scopes.ActivityScope
import android.thegoodparts.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

}
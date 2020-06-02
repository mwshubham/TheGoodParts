package app.thegoodparts.di.modules

import app.thegoodparts.di.modules.activity.MainActivityModule
import app.thegoodparts.di.scopes.ActivityScope
import app.thegoodparts.ui.activities.MainActivity
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
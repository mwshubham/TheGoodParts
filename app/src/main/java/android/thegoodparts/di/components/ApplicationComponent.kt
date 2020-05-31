package android.thegoodparts.di.components

import android.content.Context
import android.thegoodparts.MainApplication
import android.thegoodparts.di.modules.ActivityBuilderModule
import android.thegoodparts.di.modules.CoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance appContext: Context): ApplicationComponent
    }
}
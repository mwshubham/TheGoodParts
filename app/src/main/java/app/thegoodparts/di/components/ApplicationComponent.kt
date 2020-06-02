package app.thegoodparts.di.components

import android.content.Context
import app.thegoodparts.MainApplication
import app.thegoodparts.di.modules.*
import app.thegoodparts.di.modules.network.NetworkModule
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
        ActivityBuilderModule::class,
        DatabaseModule::class,

        NetworkModule::class,
        NewsModule::class,
        SearchModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance appContext: Context): ApplicationComponent
    }
}
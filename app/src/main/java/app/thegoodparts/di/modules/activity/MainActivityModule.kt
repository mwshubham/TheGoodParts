package app.thegoodparts.di.modules.activity

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.modules.fragments.*
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.activities.MainActivityVM
import app.thegoodparts.ui.fragments.home.HomeFragment
import app.thegoodparts.ui.fragments.listen.ListenFragment
import app.thegoodparts.ui.fragments.more.MoreFragment
import app.thegoodparts.ui.fragments.newsdetails.NewsDetailsFragment
import app.thegoodparts.ui.fragments.read.ReadFragment
import app.thegoodparts.ui.fragments.search.SearchFragment
import app.thegoodparts.ui.fragments.watch.WatchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    internal abstract fun bindMainActivityVM(viewModel: MainActivityVM): ViewModel

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [NewsDetailsModule::class])
    abstract fun bindNewsDetailsFragment(): NewsDetailsFragment

    @ContributesAndroidInjector(modules = [ReadFragmentModule::class])
    abstract fun bindReadFragment(): ReadFragment

    @ContributesAndroidInjector(modules = [WatchFragmentModule::class])
    abstract fun bindWatchFragment(): WatchFragment

    @ContributesAndroidInjector(modules = [ListenFragmentModule::class])
    abstract fun bindListenFragment(): ListenFragment

    @ContributesAndroidInjector(modules = [MoreFragmentModule::class])
    abstract fun bindMoreFragment(): MoreFragment

    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun bindSearchFragment(): SearchFragment

}
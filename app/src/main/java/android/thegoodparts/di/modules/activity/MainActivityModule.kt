package android.thegoodparts.di.modules.activity

import android.thegoodparts.di.modules.fragments.*
import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.activities.MainActivityVM
import android.thegoodparts.ui.fragments.home.HomeFragment
import android.thegoodparts.ui.fragments.listen.ListenFragment
import android.thegoodparts.ui.fragments.more.MoreFragment
import android.thegoodparts.ui.fragments.read.ReadFragment
import android.thegoodparts.ui.fragments.search.SearchFragment
import android.thegoodparts.ui.fragments.watch.WatchFragment
import androidx.lifecycle.ViewModel
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
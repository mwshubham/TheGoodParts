package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.home.HomeFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class HomeFragmentModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentVM::class)
    internal abstract fun bindHomeFragmentVM(viewModel: HomeFragmentVM): ViewModel

}
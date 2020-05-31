package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.home.HomeFragmentVM
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentVM::class)
    internal abstract fun bindHomeFragmentVM(viewModel: HomeFragmentVM): ViewModel

}
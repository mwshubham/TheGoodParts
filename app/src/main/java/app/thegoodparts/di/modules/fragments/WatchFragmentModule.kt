package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.watch.WatchFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WatchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(WatchFragmentVM::class)
    internal abstract fun bindWatchFragmentVM(viewModel: WatchFragmentVM): ViewModel

}
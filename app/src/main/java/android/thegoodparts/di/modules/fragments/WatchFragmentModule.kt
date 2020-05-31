package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.watch.WatchFragmentVM
import androidx.lifecycle.ViewModel
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
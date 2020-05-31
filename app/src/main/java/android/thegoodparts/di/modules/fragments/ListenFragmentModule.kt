package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.listen.ListenFragmentVM
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListenFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListenFragmentVM::class)
    internal abstract fun bindListenFragmentVM(viewModel: ListenFragmentVM): ViewModel

}
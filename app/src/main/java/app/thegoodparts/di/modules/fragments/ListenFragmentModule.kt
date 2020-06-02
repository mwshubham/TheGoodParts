package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.listen.ListenFragmentVM
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
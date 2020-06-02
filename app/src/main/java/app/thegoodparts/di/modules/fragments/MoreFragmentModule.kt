package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.more.MoreFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MoreFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoreFragmentVM::class)
    internal abstract fun bindMoreFragmentVM(viewModel: MoreFragmentVM): ViewModel

}
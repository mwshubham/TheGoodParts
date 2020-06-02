package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.read.ReadFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ReadFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReadFragmentVM::class)
    internal abstract fun bindReadFragmentVM(viewModel: ReadFragmentVM): ViewModel

}
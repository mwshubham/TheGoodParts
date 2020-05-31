package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.read.ReadFragmentVM
import androidx.lifecycle.ViewModel
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
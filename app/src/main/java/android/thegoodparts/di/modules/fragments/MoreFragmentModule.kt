package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.more.MoreFragmentVM
import androidx.lifecycle.ViewModel
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
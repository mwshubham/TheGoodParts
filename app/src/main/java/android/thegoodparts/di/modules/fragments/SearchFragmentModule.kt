package android.thegoodparts.di.modules.fragments

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.fragments.search.SearchFragmentVM
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFragmentVM::class)
    internal abstract fun bindSearchFragmentVM(viewModel: SearchFragmentVM): ViewModel

}
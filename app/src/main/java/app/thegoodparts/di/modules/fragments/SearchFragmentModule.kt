package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.search.SearchFragmentVM
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
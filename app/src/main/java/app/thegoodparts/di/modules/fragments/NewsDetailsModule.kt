package app.thegoodparts.di.modules.fragments

import androidx.lifecycle.ViewModel
import app.thegoodparts.di.scopes.ViewModelKey
import app.thegoodparts.ui.fragments.newsdetails.NewsDetailsFragmentVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class NewsDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsFragmentVM::class)
    internal abstract fun bindNewsDetailsFragmentVM(viewModel: NewsDetailsFragmentVM): ViewModel

}
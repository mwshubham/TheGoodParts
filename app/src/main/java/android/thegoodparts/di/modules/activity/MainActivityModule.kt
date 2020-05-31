package android.thegoodparts.di.modules.activity

import android.thegoodparts.di.scopes.ViewModelKey
import android.thegoodparts.ui.activities.MainActivityVM
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    internal abstract fun bindMainActivityVM(viewModel: MainActivityVM): ViewModel


}
package app.thegoodparts.ui.fragments.more

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber

class MoreFragmentVM
@ViewModelInject constructor() : ViewModel() {

    init {
        Timber.i("init{}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}

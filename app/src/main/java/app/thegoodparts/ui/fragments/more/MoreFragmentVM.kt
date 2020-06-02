package app.thegoodparts.ui.fragments.more

import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

class MoreFragmentVM
@Inject constructor() : ViewModel() {

    init {
        Timber.i("init{}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}

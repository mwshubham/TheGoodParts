package app.thegoodparts.ui.fragments.sortby

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.thegoodparts.constants.CoreLoggingConstants
import timber.log.Timber

class SortTypeEventViewModel : ViewModel() {
    init {
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }

    private val _event = MutableLiveData<SortTypeEvent>()
    val event: LiveData<SortTypeEvent>
        get() = _event

    fun sendEvent(sortFor: SortFor, sortType: SortType) {
        _event.value = SortTypeEvent(sortFor, sortType)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }

}
package app.thegoodparts.ui.fragments.companylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.thegoodparts.ApiState
import app.thegoodparts.constants.CoreLoggingConstants
import app.thegoodparts.data.repository.JsonGeneratorRepository
import app.thegoodparts.data.source.remote.response.CompanyMemberRelation
import app.thegoodparts.ui.fragments.sortby.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber

@ExperimentalCoroutinesApi
class CompanyListViewModel
@ViewModelInject constructor(
    private val jsonGeneratorRepository: JsonGeneratorRepository
) : ViewModel() {

    init {
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }

    private val _companies = jsonGeneratorRepository.getCompanies().asLiveData()
    val companies: LiveData<ApiState<List<CompanyMemberRelation>>>
        get() = _companies

    fun getAllSorted(sortType: SortType) = jsonGeneratorRepository.getAllSorted(sortType)

    fun toggleFollowingState(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            jsonGeneratorRepository.toggleFollowingState(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i(CoreLoggingConstants.LOGGING_PLACEHOLDER)
    }
}
package app.thegoodparts.ui.fragments.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.thegoodparts.ApiState
import app.thegoodparts.constants.ErrorType
import app.thegoodparts.data.repository.SearchRepository
import app.thegoodparts.data.source.remote.response.SearchResponse
import app.thegoodparts.ui.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchFragmentVM
@ViewModelInject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    private val _searchViewState = MutableLiveData<SearchViewState>()
    val searchViewState: LiveData<SearchViewState>
        get() = _searchViewState

    private val _apiState = MutableLiveData<ApiState<SearchResponse>>()
    val apiState: LiveData<ApiState<SearchResponse>>
        get() = _apiState

//    private val _data = MutableLiveData<Searc>()
//    val data: LiveData<DoubtnutDataResponse>
//        get() = _data

    init {
        Timber.i("init{}")
        _searchViewState.postValue(SearchViewState.Cleared)
    }

    fun userTyping() {
        _searchViewState.postValue(SearchViewState.Typing)
    }

    fun clearSearchInput() {
        _searchViewState.postValue(SearchViewState.Cleared)
    }

    fun dismiss() {
        _screenState.postValue(ScreenState.Dismiss)
    }

    private fun loadingData() {
        _apiState.postValue(ApiState.Loading())
    }

    fun postSuccess() {

    }

    fun postError(errorType: ErrorType) {
        _apiState.postValue(ApiState.Error(errorType))
    }

    /**
     * Fetch address list.
     */
    fun getAddressList(queryString: String) {
        Timber.i("queryString: $queryString")
        loadingData()
        viewModelScope.launch(Dispatchers.IO) {
            Timber.i("launch{}")
            try {
                @Suppress("UNUSED_VARIABLE")
                val result = searchRepository.getAddressList(queryString)
                _apiState.postValue(ApiState.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                postError(ErrorType.UnknownError)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}
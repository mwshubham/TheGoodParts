@file:Suppress("unused")

package app.thegoodparts.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.thegoodparts.ApiState
import app.thegoodparts.data.repository.NewsRepository
import app.thegoodparts.data.source.local.NewsArticle
import app.thegoodparts.data.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeFragmentVM
@Inject constructor(
    newsRepository: NewsRepository,
    private val newsRemoteDataSource: NewsRemoteDataSource
) : ViewModel() {

    init {
        Timber.i("init{}")
        viewModelScope.launch {
            Timber.i("viewModelScope.launch")
        }
    }

    val newsArticles: LiveData<ApiState<List<NewsArticle>>> =
        newsRepository.getNewsArticles().asLiveData()

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}

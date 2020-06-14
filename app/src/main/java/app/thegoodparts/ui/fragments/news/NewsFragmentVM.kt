@file:Suppress("unused")

package app.thegoodparts.ui.fragments.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.thegoodparts.ApiState
import app.thegoodparts.data.repository.NewsRepository
import app.thegoodparts.data.source.local.NewsArticle
import app.thegoodparts.data.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class NewsFragmentVM
@ViewModelInject constructor(
    newsRepository: NewsRepository,
    private val newsRemoteDataSource: NewsRemoteDataSource
) : ViewModel() {

    init {
        Timber.i("init{}")
    }

    val newsArticles: LiveData<ApiState<List<NewsArticle>>> =
        newsRepository.getNewsArticles().asLiveData()

    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared()")
    }
}

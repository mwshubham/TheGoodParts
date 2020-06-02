package app.thegoodparts.ui.fragments.newsdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.thegoodparts.data.repository.NewsRepository
import app.thegoodparts.data.source.local.NewsArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

class NewsDetailsFragmentVM
@ExperimentalCoroutinesApi
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    lateinit var publishedAt: String

    init {
        Timber.i("init{}")
    }

    @ExperimentalCoroutinesApi
    val newsArticle: LiveData<NewsArticle> by lazy {
        newsRepository.getNewsArticle(publishedAt)
    }

}
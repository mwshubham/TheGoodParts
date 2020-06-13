package app.thegoodparts.ui.fragments.newsdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.thegoodparts.data.repository.NewsRepository
import app.thegoodparts.data.source.local.NewsArticle
import kotlinx.coroutines.ExperimentalCoroutinesApi

class NewsDetailsFragmentVM
@ExperimentalCoroutinesApi
@ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    lateinit var publishedAt: String

    @ExperimentalCoroutinesApi
    val newsArticle: LiveData<NewsArticle> by lazy {
        newsRepository.getNewsArticle(publishedAt)
    }

}
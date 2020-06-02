package app.thegoodparts.data.repository

import android.content.Context
import app.thegoodparts.ApiState
import app.thegoodparts.data.NetworkBoundResource
import app.thegoodparts.data.source.local.NewsArticle
import app.thegoodparts.data.source.local.NewsArticlesDao
import app.thegoodparts.data.source.remote.NewsRemoteDataSource
import app.thegoodparts.data.source.remote.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NewsRepository @Inject constructor(
    private val appContext: Context,
    private val newsArticlesDao: NewsArticlesDao,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {

    fun getNewsArticle(publishedAt: String) = newsArticlesDao.getNewsArticle(publishedAt)

    fun getNewsArticles(): Flow<ApiState<List<NewsArticle>>> {
        return object : NetworkBoundResource<List<NewsArticle>, NewsResponse>() {
            override suspend fun saveNetworkResult(response: NewsResponse) {
                newsArticlesDao.insertArticles(response.articles)
            }

            override fun shouldFetch(data: List<NewsArticle>?): Boolean = true
            override fun loadFromDb(): Flow<List<NewsArticle>> {
                return newsArticlesDao.getNewsArticles()
            }

            override suspend fun fetchFromNetwork(): Response<NewsResponse> {
                return newsRemoteDataSource.getTopHeadlines()
            }
        }
            .asFlow(appContext)
            .flowOn(Dispatchers.IO)
    }
}

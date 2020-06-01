package android.thegoodparts.data.source.remote

import android.thegoodparts.data.source.local.NewsArticle

/**
 * News source model describing the source details
 * and the articles under it.
 */
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)
package app.thegoodparts.data.source.remote.response

import app.thegoodparts.data.source.local.NewsArticle

/**
 * News source model describing the source details
 * and the articles under it.
 */
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)
package app.thegoodparts.data.source.remote

import app.thegoodparts.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

private const val ENDPOINT_TOP_HEADLINES = "/v2/top-headlines"

interface NewsRemoteDataSource {
    /**
     * @param country The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     * @param apiKey Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     *
     * Retrieves top headlines / latest news article using News API.
     */
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Url url: String = BuildConfig.NEWS_API_BASE_URL + ENDPOINT_TOP_HEADLINES,
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>
}


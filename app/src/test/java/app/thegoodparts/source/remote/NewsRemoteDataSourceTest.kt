package app.thegoodparts.source.remote

import app.thegoodparts.data.source.remote.NewsRemoteDataSource
import app.thegoodparts.extensions.create
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Mocking is not working as we are using @Url dynamic URL with Retrofit2
 */
@RunWith(JUnit4::class)
class NewsRemoteDataSourceTest : BaseServiceTest() {

    private lateinit var newsRemoteDataSource: NewsRemoteDataSource

    @Before
    @Throws(IOException::class)
    fun createService() {
        newsRemoteDataSource = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create()
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun getTopHeadlines() = runBlocking {
        println("getTopHeadlines()")
        enqueueResponse("top_headlines.json")
        val newsSource = newsRemoteDataSource.getTopHeadlines().body() ?: return@runBlocking
        println("newsSource: $newsSource")
        // Dummy request
        mockWebServer.takeRequest(1, TimeUnit.SECONDS)

        println("Matching...")
        // Check news source
        MatcherAssert.assertThat(newsSource, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(newsSource.status, CoreMatchers.`is`("ok"))
        MatcherAssert.assertThat(newsSource.totalResults, CoreMatchers.`is`(2))
        MatcherAssert.assertThat(newsSource.articles.size, CoreMatchers.`is`(2))

        // Check list
        val articles = newsSource.articles
        MatcherAssert.assertThat(articles, CoreMatchers.notNullValue())

        // Check item 1
        val article1 = articles[0]
        MatcherAssert.assertThat(article1, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(article1.source, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(article1.source!!.id, CoreMatchers.`is`("id"))
        MatcherAssert.assertThat(article1.source!!.name, CoreMatchers.`is`("name"))
        MatcherAssert.assertThat(article1.author, CoreMatchers.`is`("author"))
        MatcherAssert.assertThat(article1.title, CoreMatchers.`is`("title"))
        MatcherAssert.assertThat(article1.description, CoreMatchers.`is`("description"))
        MatcherAssert.assertThat(article1.url, CoreMatchers.`is`("url"))
        MatcherAssert.assertThat(article1.urlToImage, CoreMatchers.`is`("urlToImage"))
        MatcherAssert.assertThat(article1.publishedAt, CoreMatchers.`is`("publishedAt"))
    }
}
package app.thegoodparts.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.thegoodparts.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsArticlesDaoTest {

    private lateinit var db: MainDatabase

    // A JUnit Test Rule that swaps the background executor used by the
    // Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        println("initDb")
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MainDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        println("closeDb")
        db.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertArticles_addingArticle_returnOneLongRowId() {
        println("insertArticles_addingArticle_returnOneLongRowId")
        val newsArticle = NewsArticle(
            source = NewsSource(
                null,
                "Ndtv.com"
            ),
            author = null,
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = "publishedAt"
        )
        val input = listOf(newsArticle)

        val rowId1 = db.newsArticlesDao().insertArticles(input)
        println("rowId1: $rowId1")
        MatcherAssert.assertThat(rowId1, CoreMatchers.equalTo(listOf(1L)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertArticles_addingArticleTwice_returnPositiveOneNegativeOneLongRowId() {
        println("insertArticles_addingArticleTwice_returnPositiveOneNegativeOneLongRowId")
        val newsArticle = NewsArticle(
            source = NewsSource(
                null,
                "Ndtv.com"
            ),
            author = null,
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = "publishedAt"
        )
        val input = listOf(newsArticle)
        val rowId1 = db.newsArticlesDao().insertArticles(input)
        val rowId2 = db.newsArticlesDao().insertArticles(input)
        println("rowId1: $rowId1")  // Inserted Successfully.
        println("rowId2: $rowId2") // Ignored.
        MatcherAssert.assertThat(rowId1, CoreMatchers.equalTo(listOf(1L)))
        MatcherAssert.assertThat(rowId2, CoreMatchers.equalTo(listOf(-1L)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun getNewsArticle_insertArticle_compareValues() {
        println("getNewsArticle_insertArticle_returnSize")
        val newsArticle = NewsArticle(
            source = NewsSource(
                null,
                "Ndtv.com"
            ),
            author = null,
            title = "title",
            description = "description",
            url = "url",
            urlToImage = "urlToImage",
            publishedAt = "publishedAt"
        )
        val input = listOf(newsArticle)
        db.newsArticlesDao().insertArticles(input)
        val article = db.newsArticlesDao().getNewsArticle("publishedAt").getOrAwaitValue()
        println(article.title)
        MatcherAssert.assertThat(article.source, CoreMatchers.equalTo(newsArticle.source))
        MatcherAssert.assertThat(article.author, CoreMatchers.equalTo(newsArticle.author))
        MatcherAssert.assertThat(article.title, CoreMatchers.equalTo(newsArticle.title))
        MatcherAssert.assertThat(article.description, CoreMatchers.equalTo(newsArticle.description))
        MatcherAssert.assertThat(article.url, CoreMatchers.equalTo(newsArticle.url))
        MatcherAssert.assertThat(article.urlToImage, CoreMatchers.equalTo(newsArticle.urlToImage))
        MatcherAssert.assertThat(article.publishedAt, CoreMatchers.equalTo(newsArticle.publishedAt))
    }
}
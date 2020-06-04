package app.thegoodparts.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Defines access layer to news articles table
 */
@Dao
interface NewsArticlesDao {
    /**
     * Insert articles into the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<NewsArticle>): List<Long>

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

    @Query("SELECT * FROM news_article where publishedAt =:publishedAt")
    fun getNewsArticle(publishedAt: String): LiveData<NewsArticle>

    /**
     * Get all the articles from table
     */
    @Query("SELECT * FROM news_article")
    fun getNewsArticles(): Flow<List<NewsArticle>>
}
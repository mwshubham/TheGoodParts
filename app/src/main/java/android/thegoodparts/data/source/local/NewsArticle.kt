package android.thegoodparts.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * News Article Model describing the article details
 * fetched from news source.
 *
 * Warning: Ideally network and database model should be different.
 * They are same for this sample app.
 */
@Entity(tableName = NewsArticle.Companion.TableInfo.tableName)
data class NewsArticle(

    @Embedded
    val source: NewsSource? = null,

    /**
     * Name of the author for the article
     */
    @ColumnInfo(name = Column.author)
    val author: String? = null,

    /**
     * Title of the article
     */
    @ColumnInfo(name = Column.title)
    val title: String? = null,

    /**
     * Complete description of the article
     */
    @ColumnInfo(name = Column.description)
    val description: String? = null,

    /**
     * URL to the article
     */
    @ColumnInfo(name = Column.url)
    val url: String? = null,

    /**
     * URL of the artwork shown with article
     */
    @ColumnInfo(name = Column.urlToImage)
    val urlToImage: String? = null,

    /**
     * Date-time when the article was published
     */
    @PrimaryKey
    @ColumnInfo(name = Column.publishedAt)
    val publishedAt: String
) {

    companion object {

        object TableInfo {
            const val tableName = "news_article"
        }

        object Column {
            const val id = "id"
            const val source = "source"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
        }
    }

}
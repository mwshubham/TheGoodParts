package app.thegoodparts.data.source.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.thegoodparts.data.source.remote.response.Company
import app.thegoodparts.data.source.remote.response.Member

/**
 * main room database...
 */
@Database(
    entities = [
        NewsArticle::class,
        Company::class,
        Member::class
    ],
    exportSchema = false,
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticlesDao
    abstract fun companyDao(): CompanyDao
    abstract fun memberDao(): MemberDao

    companion object {

        private const val databaseName = "main-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, MainDatabase::class.java, databaseName)
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java)
                .build()
    }
}
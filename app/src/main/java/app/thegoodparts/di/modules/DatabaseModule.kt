@file:Suppress("KDocUnresolvedReference")

package app.thegoodparts.di.modules

import android.content.Context
import app.thegoodparts.data.source.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [ApplicationComponent].
 *
 * Considering [NewsDatabase] as a main database for now...
 *
 * @see Module
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    /**
     * Create a provider method binding for Main Database.
     *
     * @return Instance of main database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMainDatabase(@ApplicationContext applicationContext: Context) =
        MainDatabase.buildDefault(applicationContext)

    /**
     * Create a provider method binding for [NewsArticlesDao].
     *
     * @return Instance of news article data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideNewsArticlesDao(newsDatabase: MainDatabase) =
        newsDatabase.newsArticlesDao()

}
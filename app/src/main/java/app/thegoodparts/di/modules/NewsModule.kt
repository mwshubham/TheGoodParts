package app.thegoodparts.di.modules

import android.content.Context
import app.thegoodparts.data.repository.NewsRepository
import app.thegoodparts.data.source.local.NewsArticlesDao
import app.thegoodparts.data.source.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NewsModule {

    /**
     * Create a provider method binding for [NewsRemoteDataSource].
     *
     * @return Instance of News Remote Data Source.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(retrofit: Retrofit): NewsRemoteDataSource =
        retrofit.create(NewsRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [NewsRepository].
     *
     * @return Instance of Doubtnut Repository.
     * @see Provides
     */
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNewsRepository(
        @ApplicationContext applicationContext: Context,
        newsArticlesDao: NewsArticlesDao,
        newsRemoteDataSource: NewsRemoteDataSource
    ) =
        NewsRepository(
            applicationContext,
            newsArticlesDao,
            newsRemoteDataSource
        )

}
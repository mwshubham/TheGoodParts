package app.thegoodparts.di.modules

import app.thegoodparts.data.repository.SearchRepository
import app.thegoodparts.data.source.remote.SearchRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SearchModule {

    /**
     * Create a provider method binding for [SearchRemoteDataSource].
     *
     * @return Instance of Search Remote Data Source.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(retrofit: Retrofit): SearchRemoteDataSource =
        retrofit.create(SearchRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [SearchRepository].
     *
     * @return Instance of Search Repository.
     * @see Provides
     */
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNewsRepository(
        searchRemoteDataSource: SearchRemoteDataSource
    ) =
        SearchRepository(searchRemoteDataSource)

}
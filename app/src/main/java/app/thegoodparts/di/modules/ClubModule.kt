package app.thegoodparts.di.modules

import android.content.Context
import app.thegoodparts.data.repository.JsonGeneratorRepository
import app.thegoodparts.data.source.local.CompanyDao
import app.thegoodparts.data.source.local.MemberDao
import app.thegoodparts.data.source.remote.JsonGeneratorRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ClubModule {

    /**
     * Create a provider method binding for [JsonGeneratorRemoteDataSource].
     *
     * @return Instance of Json Generator Remote Data Source.
     */
    @Singleton
    @Provides
    fun provideJsonGeneratorRemoteDataSource(retrofit: Retrofit): JsonGeneratorRemoteDataSource =
        retrofit.create(JsonGeneratorRemoteDataSource::class.java)

    /**
     * Create a provider method binding for [JsonGeneratorRepository].
     *
     * @return Instance of Json Generator Repository.
     */
    @Singleton
    @Provides
    fun provideJsonGeneratorRepository(
        @ApplicationContext applicationContext: Context,
        jsonGeneratorRemoteDataSource: JsonGeneratorRemoteDataSource,
        companyDao: CompanyDao,
        memberDao: MemberDao
    ) = JsonGeneratorRepository(
        applicationContext,
        companyDao,
        memberDao,
        jsonGeneratorRemoteDataSource
    )
}
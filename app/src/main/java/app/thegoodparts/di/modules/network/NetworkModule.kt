package app.thegoodparts.di.modules.network

import android.content.Context
import app.thegoodparts.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * @see Module
 */
@Module
class NetworkModule {

    /**
     * Create a provider method binding for [HttpLoggingInterceptor].
     *
     * @return Instance of http interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [StethoInterceptor].
     *
     * @return Instance of stetho interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    /**
     * Create a provider method binding for [ChuckerInterceptor].
     *
     * @return Instance of chucker interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideChuckerInterceptor(appContext: Context): ChuckerInterceptor {
        return ChuckerInterceptor(appContext)
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(httpLoggingInterceptor)
            clientBuilder.addInterceptor(chuckerInterceptor)
            clientBuilder.addNetworkInterceptor(stethoInterceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Create a provider method binding for [Retrofit].
     *
     * @return Instance of retrofit.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.AWS_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}
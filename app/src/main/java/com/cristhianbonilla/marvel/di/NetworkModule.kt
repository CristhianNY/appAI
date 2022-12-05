package com.cristhianbonilla.marvel.di

import com.cristhianbonilla.marvel.BuildConfig
import com.cristhianbonilla.marvel.NetworkInterceptor
import com.cristhianbonilla.support.config.md5
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val API_KEY = "apikey"
private const val TIME_STAMP_KEY = "ts"
private const val HASH_KEY = "hash"
private const val CONTENT_TYPE_KEY = "Content-Type"
private const val CONTENT_TYPE = "application/json"
private const val HEADER_ACCEPT_KEY = "Accept"
private const val HEADER_ACCEPT = "application/json"
private const val TIMEOUT = 90L
private const val DIVIDER_TIMESTAMP = 100
private const val TS = ""

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(NetworkInterceptor.httpLogging())

        httpClient.addInterceptor { chain ->
            val ts = "1"
            val hash = ts.plus(BuildConfig.PRIVATE_KEY).plus(BuildConfig.PUBLIC_KEY)
            val original = chain.request()
            val originalHttpUrl =
                original.url.newBuilder().addQueryParameter(TIME_STAMP_KEY, ts)
                    .addQueryParameter(API_KEY, BuildConfig.PUBLIC_KEY)
                    .addQueryParameter(HASH_KEY, hash.md5()).build()

            val requestBuilder = original.newBuilder().url(originalHttpUrl)
            requestBuilder.header(CONTENT_TYPE_KEY, CONTENT_TYPE)
            requestBuilder.header(HEADER_ACCEPT_KEY, HEADER_ACCEPT)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }
}

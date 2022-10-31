package com.leverx.challenge.core.di.data

import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.interceptor.Interceptors
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class NetworkModule {

    @Provides
    @Singleton
    fun provideFlickrApiService(
        retrofit: Retrofit,
    ): FlickrApiService =
        retrofit.create(FlickrApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(" https://www.flickr.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val apiKey = "9f1a654fcf398e59e524a7d09146a484" // TODO
        val apiKeyInterceptor = Interceptors.FlickrApiKeyInterceptor(apiKey)
        return OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
package com.leverx.challenge.core.di.data

import android.content.Context
import com.leverx.challenge.core.R
import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.interceptor.Interceptors
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("https://www.flickr.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val apiKey = context.getString(R.string.flickr_api_key) // TODO
        val apiKeyInterceptor = Interceptors.FlickrApiKeyInterceptor(apiKey)
        return OkHttpClient.Builder()
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
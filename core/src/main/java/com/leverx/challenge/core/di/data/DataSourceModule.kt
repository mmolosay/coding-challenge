package com.leverx.challenge.core.di.data

import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.datasources.RemoteImageDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteImagesDataSource(
        api: FlickrApiService,
    ): RemoteImageDataSource =
        RemoteImageDataSource(api)
}
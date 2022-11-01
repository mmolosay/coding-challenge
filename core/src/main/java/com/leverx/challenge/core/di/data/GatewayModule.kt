package com.leverx.challenge.core.di.data

import com.leverx.challenge.data.gateways.ImagesGatewayImpl
import com.leverx.challenge.data.gateways.ViewedImagesGatewayImpl
import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.local.datasources.LocalViewedImagesDataSource
import com.leverx.challenge.remote.datasources.RemoteImageDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class GatewayModule {

    @Provides
    @Singleton
    fun provideImagesGateway(
        dataSource: RemoteImageDataSource,
    ): ImagesGateway =
        ImagesGatewayImpl(dataSource)

    @Provides
    @Singleton
    fun provideViewedImagesGateway(
        dataSource: LocalViewedImagesDataSource,
    ): ViewedImagesGateway =
        ViewedImagesGatewayImpl(dataSource)
}
package com.leverx.challenge.core.di.domain

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.usecase.UseCase
import com.leverx.challenge.domain.usecase.impl.AddViewedImageUseCaseImpl
import com.leverx.challenge.domain.usecase.impl.GetImagesUseCaseImpl
import com.leverx.challenge.domain.usecase.impl.GetViewedImagesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@Module
@DisableInstallInCheck
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetImagesUseCase(
        gateway: ImagesGateway,
    ): UseCase.GetImages =
        GetImagesUseCaseImpl(gateway)

    @Provides
    @Singleton
    fun provideGetViewedImagesUseCase(
        viewedImagesGateway: ViewedImagesGateway,
    ): UseCase.GetViewedImages =
        GetViewedImagesUseCaseImpl(
            viewedImagesGateway,
        )

    @Provides
    @Singleton
    fun provideAddViewedImageUseCase(
        imagesGateway: ImagesGateway,
        viewedImagesGateway: ViewedImagesGateway,
    ): UseCase.AddViewedImage =
        AddViewedImageUseCaseImpl(
            imagesGateway,
            viewedImagesGateway,
        )
}
package com.leverx.challenge.domain.usecase.impl

import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.model.ViewedImage
import com.leverx.challenge.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class GetViewedImagesUseCaseImpl(
    private val viewedImagesGateway: ViewedImagesGateway,
) : UseCase.GetViewedImages {

    override suspend fun invoke(): Flow<List<ViewedImage>> =
        viewedImagesGateway.getViewedImages()
}
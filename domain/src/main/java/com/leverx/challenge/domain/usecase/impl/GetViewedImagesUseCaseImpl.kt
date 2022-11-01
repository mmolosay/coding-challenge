package com.leverx.challenge.domain.usecase.impl

import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.model.ViewedImage
import com.leverx.challenge.domain.usecase.UseCase

class GetViewedImagesUseCaseImpl(
    private val viewedImagesGateway: ViewedImagesGateway,
) : UseCase.GetViewedImages {

    override suspend fun invoke(): List<ViewedImage> =
        viewedImagesGateway.getViewedImages()
}
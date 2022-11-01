package com.leverx.challenge.domain.usecase.impl

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.usecase.UseCase

class AddViewedImageUseCaseImpl(
    private val imagesGateway: ImagesGateway,
    private val viewedImagesGateway: ViewedImagesGateway,
) : UseCase.AddViewedImage {

    override suspend fun invoke(id: Long): Boolean {
        val images = imagesGateway.getCachedImages()
        val image = images.find { it.id == id } ?: return false // no image with specified id
        viewedImagesGateway.addViewedImage(image)
        return true
    }
}
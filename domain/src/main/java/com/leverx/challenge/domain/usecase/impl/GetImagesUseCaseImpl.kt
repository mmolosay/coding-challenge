package com.leverx.challenge.domain.usecase.impl

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.domain.model.RemoteImagesRequest
import com.leverx.challenge.domain.usecase.UseCase

class GetImagesUseCaseImpl(
    private val gateway: ImagesGateway,
) : UseCase.GetImages {

    override suspend fun invoke(request: RemoteImagesRequest): RemoteImages =
        gateway.getRemoteImages(request)
}
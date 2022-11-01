package com.leverx.challenge.domain.usecase.impl

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ImagesRequest
import com.leverx.challenge.domain.usecase.UseCase

class GetImagesUseCaseImpl(
    private val gateway: ImagesGateway,
) : UseCase.GetImages {

    override suspend fun invoke(request: ImagesRequest): ImagesData =
        gateway.getImagesData(request)
}
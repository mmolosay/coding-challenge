package com.leverx.challenge.data.gateways

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ImagesRequest
import com.leverx.challenge.remote.datasources.RemoteImageDataSource
import com.leverx.challenge.remote.model.mappers.toRequest

class ImagesGatewayImpl(
    private val dataSource: RemoteImageDataSource,
) : ImagesGateway {

    private val imageData = mutableListOf<ImagesData>()

    override suspend fun getImagesData(request: ImagesRequest) =
        dataSource.fetchRemoteImages(request.toRequest()).also { images ->
            imageData.add(images)
        }

    override suspend fun getCachedImages(): List<ImagesData.RemoteImage> =
        imageData.flatMap { it.images ?: emptyList() }
}
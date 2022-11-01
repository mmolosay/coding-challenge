package com.leverx.challenge.data.gateways

import com.leverx.challenge.domain.gateway.ImagesGateway
import com.leverx.challenge.domain.model.RemoteImagesRequest
import com.leverx.challenge.remote.datasources.RemoteImageDataSource
import com.leverx.challenge.remote.model.mappers.toRequest

class ImagesGatewayImpl(
    private val dataSource: RemoteImageDataSource,
) : ImagesGateway {

    override suspend fun getRemoteImages(request: RemoteImagesRequest) =
        dataSource.fetchImagesData(request.toRequest())
}
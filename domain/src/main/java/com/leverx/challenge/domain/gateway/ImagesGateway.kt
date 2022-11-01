package com.leverx.challenge.domain.gateway

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.domain.model.RemoteImagesRequest

interface ImagesGateway {
    suspend fun getRemoteImages(request: RemoteImagesRequest): RemoteImages
}
package com.leverx.challenge.domain.gateway

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.domain.model.RemoteImagesRequest
import kotlinx.coroutines.flow.Flow

interface ImagesGateway {
    suspend fun getRemoteImages(request: RemoteImagesRequest): Flow<RemoteImages>
}
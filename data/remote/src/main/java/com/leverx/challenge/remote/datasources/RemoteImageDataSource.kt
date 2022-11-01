package com.leverx.challenge.remote.datasources

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.model.ImagesRequest
import com.leverx.challenge.remote.model.mappers.toDomain

class RemoteImageDataSource(
    private val api: FlickrApiService,
) {

    suspend fun fetchImagesData(request: ImagesRequest): RemoteImages =
        api.getImages(
            query = request.query,
        ).toDomain()
}
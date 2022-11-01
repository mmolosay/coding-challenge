package com.leverx.challenge.remote.datasources

import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.model.ImagesRequest
import com.leverx.challenge.remote.model.mappers.toDomain

class RemoteImageDataSource(
    private val api: FlickrApiService,
) {

    /**
     * Fetches [ImagesData] from server.
     */
    suspend fun fetchRemoteImages(request: ImagesRequest): ImagesData =
        api.getImages(
            query = request.query,
        ).toDomain()
}
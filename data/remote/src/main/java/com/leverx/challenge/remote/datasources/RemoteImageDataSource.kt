package com.leverx.challenge.remote.datasources

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.remote.api.FlickrApiService
import com.leverx.challenge.remote.model.ImagesRequest
import com.leverx.challenge.remote.model.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteImageDataSource(
    private val api: FlickrApiService,
) {

    suspend fun fetchImagesData(request: ImagesRequest): Flow<RemoteImages> =
        flow {
            val response = api.getImages(
                query = request.query,
            )
            emit(response.toDomain())
        }
}
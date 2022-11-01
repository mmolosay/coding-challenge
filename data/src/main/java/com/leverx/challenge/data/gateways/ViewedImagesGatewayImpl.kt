package com.leverx.challenge.data.gateways

import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ViewedImage
import com.leverx.challenge.local.datasources.LocalViewedImagesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ViewedImagesGatewayImpl(
    private val dataSource: LocalViewedImagesDataSource,
) : ViewedImagesGateway {

    val imagesFlow = MutableStateFlow<List<ViewedImage>>(emptyList())

    override suspend fun getViewedImages(): Flow<List<ViewedImage>> =
        updateImagesFlow()

    override suspend fun addViewedImage(image: ImagesData.RemoteImage) {
        val viewed = ViewedImage(
            id = image.id,
            url = image.url,
            title = image.title,
        )
        dataSource.addViewedImage(viewed)
        updateImagesFlow()
    }

    /**
     * Updates [imagesFlow] with [LocalViewedImagesDataSource.getViewedImages]
     * and returns it.
     */
    private fun updateImagesFlow(): Flow<List<ViewedImage>> =
        imagesFlow.apply {
            update { dataSource.getViewedImages() }
        }
}
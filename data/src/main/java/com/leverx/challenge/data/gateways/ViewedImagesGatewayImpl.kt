package com.leverx.challenge.data.gateways

import com.leverx.challenge.domain.gateway.ViewedImagesGateway
import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ViewedImage
import com.leverx.challenge.local.datasources.LocalViewedImagesDataSource

class ViewedImagesGatewayImpl(
    private val dataSource: LocalViewedImagesDataSource,
) : ViewedImagesGateway {

    override suspend fun getViewedImages(): List<ViewedImage> =
        dataSource.getViewedImages()

    override suspend fun addViewedImage(image: ImagesData.RemoteImage) {
        val viewed = ViewedImage(
            id = image.id,
            url = image.url,
            title = image.title,
        )
        dataSource.addViewedImage(viewed)
    }
}
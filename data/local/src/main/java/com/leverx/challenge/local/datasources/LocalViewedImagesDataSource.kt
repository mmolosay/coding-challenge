package com.leverx.challenge.local.datasources

import com.leverx.challenge.domain.model.ViewedImage

class LocalViewedImagesDataSource {

    private val images = mutableListOf<ViewedImage>()

    fun getViewedImages(): List<ViewedImage> =
        images

    fun addViewedImage(image: ViewedImage) {
        images.add(image)
    }
}
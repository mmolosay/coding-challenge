package com.leverx.challenge.local.datasources

import com.leverx.challenge.domain.model.ViewedImage

class LocalViewedImagesDataSource {

    private var images = listOf<ViewedImage>()

    fun getViewedImages(): List<ViewedImage> =
        images

    fun addViewedImage(image: ViewedImage) {
        this.images = images + image // create new list to satisfy strong equality-based conflation
    }
}
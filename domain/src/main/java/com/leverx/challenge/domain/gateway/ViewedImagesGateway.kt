package com.leverx.challenge.domain.gateway

import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ViewedImage

/**
 * Gateway of previously viewed (interacted with) by user images.
 */
interface ViewedImagesGateway {

    /**
     * Retrieves list of [ViewedImage]s.
     * The images appear in the order they were viewed: from most old to most new.
     *
     * Viewed images can be added via [addViewedImage].
     */
    suspend fun getViewedImages(): List<ViewedImage>

    /**
     * Adds specified [image] into the list of viewed ones.
     *
     * Viewd images can be obtained from [getViewedImages].
     */
    suspend fun addViewedImage(image: ImagesData.RemoteImage)
}
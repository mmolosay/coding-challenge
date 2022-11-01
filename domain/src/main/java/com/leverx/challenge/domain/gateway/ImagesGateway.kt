package com.leverx.challenge.domain.gateway

import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ImagesRequest

/**
 * Gateway of [ImagesData].
 */
interface ImagesGateway {

    /**
     * Retrieves [ImagesData] that match specified [request].
     */
    suspend fun getImagesData(request: ImagesRequest): ImagesData

    /**
     * Retrieves cached [ImagesData.RemoteImage]s, obtained via [getImagesData] in current session.
     */
    suspend fun getCachedImages(): List<ImagesData.RemoteImage>
}
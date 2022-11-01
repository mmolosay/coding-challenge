package com.leverx.challenge.model

/**
 * @see com.leverx.challenge.domain.model.ImagesData
 */
data class ImagesData(
    val images: List<RemoteImage>?,
) {

    data class RemoteImage(
        val id: Long,
        val url: String?,
        val title: String?,
    )
}
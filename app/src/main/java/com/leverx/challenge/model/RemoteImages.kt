package com.leverx.challenge.model

/**
 * @see com.leverx.challenge.domain.model.RemoteImages
 */
data class RemoteImages(
    val images: List<Image>?,
) {

    data class Image(
        val url: String?,
    )
}
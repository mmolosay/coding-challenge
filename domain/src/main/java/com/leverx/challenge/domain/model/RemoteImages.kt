package com.leverx.challenge.domain.model

// TODO: make sealed and derive 'Failure' and 'Success' states with 'Status' enum.
/**
 * Represents various data about some remote images.
 */
data class RemoteImages(
    val images: List<Image>
) {

    /**
     * Represents not an image itself, but its remote source.
     */
    data class Image(
        val url: String?,
    )
}

data class RemoteImagesRequest(
    val query: String,
)
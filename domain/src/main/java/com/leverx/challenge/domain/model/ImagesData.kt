package com.leverx.challenge.domain.model

// TODO: make sealed and derive 'Failure' and 'Success' states with 'Status' enum.
/**
 * Represents various data about some remote images.
 */
data class ImagesData(
    val images: List<RemoteImage>?
) {

    /**
     * Represents not an image itself, but its remote source.
     */
    data class RemoteImage(
        val id: Long?,
        val url: String?,
        val title: String?,
    )
}

/**
 * Request to obtain [ImagesData] that satisfies specified parameters.
 */
data class ImagesRequest(
    val query: String,
)
package com.leverx.challenge.remote.model.mappers

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.remote.model.ImagesResponse

/*
 * Maps "response" objects to their "domain" variants.
 */

fun ImagesResponse.toDomain(): RemoteImages =
    RemoteImages(
        images = this.data?.images?.map { it.toDomain() },
    )

private fun ImagesResponse.Image.toDomain(): RemoteImages.Image =
    RemoteImages.Image(
        id = this.id,
        url = this.toUrl(),
        title = this.title,
    )

/**
 * Subtitutes data of receiver image into URL template.
 *
 * @return URL that refers to receiver image.
 */
// e.g. https://farm66.static.flickr.com/65535/45883334851_2932c49c76.jpg
private fun ImagesResponse.Image.toUrl(): String =
    "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
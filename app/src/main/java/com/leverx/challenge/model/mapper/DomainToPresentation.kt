package com.leverx.challenge.model.mapper

import com.leverx.challenge.model.ImagesData
import com.leverx.challenge.model.ViewedImage
import com.leverx.challenge.domain.model.ImagesData as DomainRemoteImages
import com.leverx.challenge.domain.model.ViewedImage as DomainViewedImage

/*
 * Maps "domain" objects to their "presentation" variants.
 */

fun DomainRemoteImages.toPresentation(): ImagesData =
    ImagesData(
        images = this.images?.mapNotNull { it.toPresentation() },
    )

private fun DomainRemoteImages.RemoteImage.toPresentation(): ImagesData.RemoteImage? {
    return ImagesData.RemoteImage(
        id = this.id ?: return null, // we don't want images without valid ID
        url = this.url,
        title = this.title,
    )
}

fun DomainViewedImage.toPresentation(): ViewedImage? {
    return ViewedImage(
        id = this.id ?: return null, // we don't want images without valid ID
        url = this.url,
        title = this.title,
    )
}

fun List<DomainViewedImage>.toPresentation(): List<ViewedImage> =
    mapNotNull { it.toPresentation() }
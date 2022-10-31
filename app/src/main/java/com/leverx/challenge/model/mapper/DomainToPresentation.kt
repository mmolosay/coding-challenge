package com.leverx.challenge.model.mapper

import com.leverx.challenge.model.RemoteImages
import com.leverx.challenge.domain.model.RemoteImages as DomainRemoteImages

/*
 * Maps "domain" objects to their "presentation" variants.
 */

fun DomainRemoteImages.toPresentation(): RemoteImages =
    RemoteImages(
        images = this.images?.map { it.toPresentation() },
    )

private fun DomainRemoteImages.Image.toPresentation(): RemoteImages.Image =
    RemoteImages.Image(
        id = this.id,
        url = this.url,
        title = this.title,
    )
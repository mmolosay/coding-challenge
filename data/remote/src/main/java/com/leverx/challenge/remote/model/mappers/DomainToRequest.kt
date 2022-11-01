package com.leverx.challenge.remote.model.mappers

import com.leverx.challenge.remote.model.ImagesRequest
import com.leverx.challenge.domain.model.ImagesRequest as DomainImagesRequest

/*
 * Maps "domain" objects to their "remote request" variants.
 */

fun DomainImagesRequest.toRequest(): ImagesRequest =
    ImagesRequest(
        query = this.query,
    )
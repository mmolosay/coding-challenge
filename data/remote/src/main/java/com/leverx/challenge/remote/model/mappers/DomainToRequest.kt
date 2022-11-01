package com.leverx.challenge.remote.model.mappers

import com.leverx.challenge.domain.model.RemoteImagesRequest
import com.leverx.challenge.remote.model.ImagesRequest

/*
 * Maps "domain" objects to their "remote request" variants.
 */

fun RemoteImagesRequest.toRequest(): ImagesRequest =
    ImagesRequest(
        query = this.query,
    )
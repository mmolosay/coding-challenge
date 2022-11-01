package com.leverx.challenge.domain.usecase

import com.leverx.challenge.domain.model.RemoteImages
import com.leverx.challenge.domain.model.RemoteImagesRequest

/**
 * Set of use case declarations.
 */
object UseCase {

    /**
     * Retrieves [RemoteImages].
     */
    interface GetImages {
        suspend operator fun invoke(request: RemoteImagesRequest): RemoteImages
    }
}
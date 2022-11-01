package com.leverx.challenge.domain.usecase

import com.leverx.challenge.domain.model.ImagesData
import com.leverx.challenge.domain.model.ImagesRequest
import com.leverx.challenge.domain.model.ViewedImage

/**
 * Set of use case declarations.
 */
object UseCase {

    /**
     * Retrieves [ImagesData].
     */
    interface GetImages {
        suspend operator fun invoke(request: ImagesRequest): ImagesData
    }

    /**
     * Retrieves [ViewedImage]s.
     */
    interface GetViewedImages {
        suspend operator fun invoke(): List<ViewedImage> // TODO: Flow?
    }

    /**
     * Adds image with specified ID to list of viewed ones.
     */
    interface AddViewedImage {

        /**
         * @return `true`, if image was found by [id] and added to viewed ones, `false` otherwise.
         */
        suspend operator fun invoke(id: Long): Boolean
    }
}
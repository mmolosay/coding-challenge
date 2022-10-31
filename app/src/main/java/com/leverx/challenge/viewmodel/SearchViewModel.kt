package com.leverx.challenge.viewmodel

import androidx.lifecycle.viewModelScope
import com.leverx.challenge.domain.model.RemoteImagesRequest
import com.leverx.challenge.domain.usecase.UseCase
import com.leverx.challenge.model.RemoteImages
import com.leverx.challenge.model.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * `ViewModel` for 'Search' UI component.
 *
 * @see [com.leverx.challenge.ui.components.home.Search]
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImages: UseCase.GetImages,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Blank)
    val uiState = _uiState.asStateFlow()

    private var getImagesJob: Job? = null

    /**
     * Obtain images from external source, match specified [query].
     */
    fun fetchImages(query: String) {
        getImagesJob?.cancel()
        val request = RemoteImagesRequest(query)
        val context = Dispatchers.IO + Job()
        this.getImagesJob = viewModelScope.launch(context) {
            try {
                val images = getImages(request)
                propagateSuccess(images.toPresentation())
            } catch (e: Exception) { // TODO: specify exact type of exception(s) once occured
                propagateFailure()
            }
        }
    }

    /**
     * Updates [uiState] with [UiState.Success] instance.
     */
    private fun propagateSuccess(images: RemoteImages) {
        _uiState.update {
            UiState.Success(
                remoteImages = images,
            )
        }
    }

    /**
     * Updates [uiState] with [UiState.Failure] instance.
     */
    private fun propagateFailure() {
        _uiState.update {
            UiState.Failure
        }
    }

    sealed interface UiState {

        /** Initial state; no actions performed, no data displayed. */
        object Blank : UiState

        /** Loading images for requested search parameters. */
        object Loading : UiState

        /** Images successfully loaded and can be displayed now. */
        class Success(
            val remoteImages: RemoteImages,
        ) : UiState

        /** Failure during loading images. */
        object Failure : UiState
    }
}
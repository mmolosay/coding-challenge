package com.leverx.challenge.viewmodel

import androidx.lifecycle.viewModelScope
import com.leverx.challenge.domain.model.ImagesRequest
import com.leverx.challenge.domain.usecase.UseCase
import com.leverx.challenge.model.ImagesData
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
 * @see com.leverx.challenge.ui.components.home.Search
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getImagesUseCase: UseCase.GetImages,
    private val addViewedImageUseCase: UseCase.AddViewedImage
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Blank)
    val uiState = _uiState.asStateFlow()

    private var getImagesJob: Job? = null
    private var addViewedImageJob: Job? = null

    /**
     * Obtain images from external source, match specified [query].
     */
    fun fetchImages(query: String) {
        getImagesJob?.cancel()
        propagateLoading()
        val request = ImagesRequest(query)
        val context = Dispatchers.IO + Job()
        this.getImagesJob = viewModelScope.launch(context) {
            try {
                val images = getImagesUseCase(request)
                propagateSuccess(images.toPresentation())
            } catch (e: Exception) { // TODO: specify exact type of exception(s) once occured
                propagateFailure()
            }
        }
    }

    /**
     * Marks image with specified [id] as viewed.
     */
    fun markImageAsViewed(id: Long?) {
        id ?: return
        addViewedImageJob?.cancel()
        val context = Dispatchers.Default + Job()
        this.addViewedImageJob = viewModelScope.launch(context) {
            addViewedImageUseCase(id)
        }
    }

    /**
     * Updates [uiState] with [UiState.Loading] instance.
     */
    private fun propagateLoading() =
        _uiState.update {
            UiState.Loading
        }

    /**
     * Updates [uiState] with [UiState.Success] instance.
     */
    private fun propagateSuccess(images: ImagesData) =
        _uiState.update {
            UiState.Success(
                imagesData = images,
            )
        }

    /**
     * Updates [uiState] with [UiState.Failure] instance.
     */
    private fun propagateFailure() =
        _uiState.update {
            UiState.Failure
        }

    override fun onCleared() {
        super.onCleared()
        getImagesJob?.cancel()
        getImagesJob = null
        addViewedImageJob?.cancel()
        addViewedImageJob = null
    }

    sealed interface UiState {

        /** Initial state; no actions performed, no data displayed. */
        object Blank : UiState

        /** Loading images for requested search parameters. */
        object Loading : UiState

        /** Images successfully loaded and can be displayed now. */
        class Success(
            val imagesData: ImagesData,
        ) : UiState

        /** Failure during loading images. */
        object Failure : UiState
    }
}
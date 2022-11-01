package com.leverx.challenge.viewmodel

import androidx.lifecycle.viewModelScope
import com.leverx.challenge.domain.usecase.UseCase
import com.leverx.challenge.model.ViewedImage
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
 * `ViewModel` for 'History' UI component.
 *
 * @see com.leverx.challenge.ui.components.home.History
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getViewedImagesUseCase: UseCase.GetViewedImages,
) : BaseViewModel() {

    private val _viewedImages = MutableStateFlow<List<ViewedImage>>(emptyList())
    val viewedImages = _viewedImages.asStateFlow()

    private var getViewedImagesJob: Job? = null

    init {
        updateViewedImages()
    }

    private fun updateViewedImages() {
        getViewedImagesJob?.cancel()
        val context = Dispatchers.Default + Job()
        viewModelScope.launch(context) {
            val images = getViewedImagesUseCase().toPresentation()
            _viewedImages.update { images }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getViewedImagesJob?.cancel()
        getViewedImagesJob = null
    }
}
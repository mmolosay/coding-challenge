package com.leverx.challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leverx.challenge.domain.usecase.UseCase
import com.leverx.challenge.viewmodel.SearchViewModel
import com.leverx.challenge.viewmodel.SearchViewModel.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.leverx.challenge.domain.model.ImagesData as DomainImagesData

/**
 * Tests for [SearchViewModel].
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class SearchViewModelTest {

    // Subject under test
    private lateinit var sutViewModel: SearchViewModel

    // Depended on component
    @MockK
    private lateinit var docGetImagesUseCase: UseCase.GetImages

    @MockK
    private lateinit var docAddViewedImageUseCase: UseCase.AddViewedImage

    // Environment
    @get:Rule
    var testCoroutineRule = TestCoroutineDespatcherRule()

    @get:Rule
    var instantExecutionRule = InstantTaskExecutorRule()

    private val job = Job()
    private val scope = TestScope(job + StandardTestDispatcher())

    @Before
    fun init() {
        MockKAnnotations.init(this)
        this.sutViewModel = SearchViewModel(
            getImagesUseCase = docGetImagesUseCase,
            addViewedImageUseCase = docAddViewedImageUseCase,
        )
    }

    /**
     * Tests, that [SearchViewModel.getImages] will propagate
     * [UiState.Success] in [SearchViewModel.uiState] flow.
     */
    @Test
    fun `test fetchImages propagates Success`() =
        scope.runTest {
            coEvery { docGetImagesUseCase(any()) } returns fakeDomainImagesData()
            sutViewModel.getImages(query = "test query")
            launch {
                val uiState = sutViewModel.uiState.first()
                Assert.assertTrue("Assert uiState is Success", uiState is UiState.Success)
            }
        }

    private fun fakeDomainImagesData(): DomainImagesData =
        DomainImagesData(
            images = listOf(
                DomainImagesData.RemoteImage(
                    id = 0L,
                    url = "test url",
                    title = "test title",
                ),
            )
        )
}
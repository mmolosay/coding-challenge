package com.leverx.challenge.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.leverx.challenge.model.ViewedImage
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset
import com.leverx.challenge.viewmodel.HistoryViewModel

// region Previews

@Preview(
    showBackground = true,
)
@Composable
private fun Search_Preview() {
    AppTheme {
        History()
    }
}

// endregion

/**
 * High-level master `History` UI component.
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun History(
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val images by viewModel.viewedImages.collectAsStateWithLifecycle()
    History(
        images = images,
    )
}

/**
 * Implementation of 'History' UI component.
 */
@Composable
fun History(
    images: List<ViewedImage>,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = Offset.Regular),
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(Offset.Halved),
    ) {
        items(
            items = images,
            key = { it.id },
        ) { image ->
            HistoryItem(image)
        }
    }
}

@Composable
private fun HistoryItem(
    image: ViewedImage,
) =
    AsyncImage(
        model = image.url,
        contentDescription = image.title,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth,
    )
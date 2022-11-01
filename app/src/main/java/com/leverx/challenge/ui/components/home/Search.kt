package com.leverx.challenge.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.leverx.challenge.R
import com.leverx.challenge.model.ImagesData
import com.leverx.challenge.ui.components.common.ImageDialog
import com.leverx.challenge.ui.environment.AppIcons
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset
import com.leverx.challenge.ui.environment.disabledContentColor
import com.leverx.challenge.viewmodel.SearchViewModel
import com.leverx.challenge.viewmodel.SearchViewModel.UiState

// region Previews

@Preview(
    showBackground = true,
)
@Composable
private fun Search_Preview() {
    AppTheme {
        Search(
            uiState = UiState.Blank,
            onSearchClick = {},
            markImageAsViewed = {}
        )
    }
}

// endregion

/**
 * High-level master `Search` UI component.
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Search(
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Search(
        uiState = uiState,
        onSearchClick = { query ->
            viewModel.getImages(query)
        },
        markImageAsViewed = { id ->
            viewModel.markImageAsViewed(id)
        }
    )
}

/**
 * Implementation of 'Search' UI component.
 */
@Composable
fun Search(
    uiState: UiState,
    onSearchClick: (String) -> Unit,
    markImageAsViewed: (Long) -> Unit,
) {
    Column {
        SearchBar(
            onSearchClick = onSearchClick,
        )
        Spacer(modifier = Modifier.height(Offset.Halved))
        SearchResult(
            uiState = uiState,
            markImageAsViewed = markImageAsViewed,
        )
    }
}

// region Search Bar

/**
 * Version of [SearchBar] with some modifiers.
 */
@Composable
private fun SearchBar(
    onSearchClick: (String) -> Unit,
) =
    SearchBar(
        modifier = Modifier
            .padding(horizontal = Offset.Regular)
            .padding(top = Offset.Halved),
        onSearchClick = onSearchClick,
    )

/**
 * Implementation of search bar.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit,
) =
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var input by remember { mutableStateOf("") }
        val isTrailingIconEnabled by remember {
            derivedStateOf(structuralEqualityPolicy()) {
                input.isNotEmpty()
            }
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .testTag(TestTags.SearchField),
            label = {
                SearchBarLabel()
            },
            trailingIcon = {
                SearchBarTrailingIcon(
                    enabled = isTrailingIconEnabled,
                    onClick = { onSearchClick(input) },
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(input)
                    keyboardController?.hide()
                }
            ),
        )
    }

/**
 * Label of search bar text field.
 */
@Composable
private fun SearchBarLabel() =
    Text(
        text = stringResource(R.string.home_search_text_field_label),
    )

/**
 * Icon to be placed at the end of search bar text field.
 */
@Composable
private fun SearchBarTrailingIcon(
    enabled: Boolean,
    onClick: () -> Unit,
) =
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.testTag(TestTags.SearchFieldTrailingIcon)
    ) {
        Icon(
            imageVector = AppIcons.Search,
            contentDescription = stringResource(R.string.cd_home_search_find)
        )
    }

// endregion

// region Search result

/**
 * Master 'Search result' UI component.
 * Represents search result, according to [uiState].
 */
@Composable
private fun SearchResult(
    uiState: UiState,
    markImageAsViewed: (Long) -> Unit,
) =
    when (uiState) {
        is UiState.Blank ->
            SearchResultBlank()
        is UiState.Loading ->
            SearchResultLoading()
        is UiState.Success ->
            SearchResultSuccess(
                uiState = uiState,
                markImageAsViewed = markImageAsViewed,
            )
        is UiState.Failure ->
            SearchResultFailure()
    }

/**
 * Implementation of 'Search result' UI component's [UiState.Blank] state.
 */
@Composable
private fun SearchResultBlank() =
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.home_search_blank),
            color = disabledContentColor(),
            style = MaterialTheme.typography.bodySmall,
        )
    }

/**
 * Implementation of 'Search result' UI component's [UiState.Loading] state.
 */
@Composable
private fun SearchResultLoading() =
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }

/**
 * Implementation of 'Search result' UI component's [UiState.Success] state.
 */
@Composable
private fun SearchResultSuccess(
    uiState: UiState.Success,
    markImageAsViewed: (Long) -> Unit,
) =
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = Offset.Regular),
        verticalArrangement = Arrangement.spacedBy(Offset.Halved),
    ) {
        items(
            items = uiState.imagesData.images ?: emptyList(),
            key = { it.id },
        ) { image ->
            ImageItem(
                image = image,
                markImageAsViewed = markImageAsViewed,
            )
        }
    }

@Composable
private fun ImageItem(
    image: ImagesData.RemoteImage,
    markImageAsViewed: (Long) -> Unit,
) {
    var showImageDialog by remember { mutableStateOf(false) }

    val model = ImageRequest.Builder(LocalContext.current)
        .data(image.url)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
    val painter = rememberAsyncImagePainter(model)

    Image(
        painter = painter,
        contentDescription = image.title,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showImageDialog = true
                markImageAsViewed(image.id)
            },
    )

    if (showImageDialog) {
        ImageDialog(onDismissRequest = { showImageDialog = false }) {
            Image(
                painter = painter,
                contentDescription = image.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Offset.Regular),
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}

@Composable
private fun SearchResultFailure() =
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.home_search_failure),
            color = disabledContentColor(),
            style = MaterialTheme.typography.bodySmall,
        )
    }

// endregion

object TestTags {
    const val SearchField = "search text field"
    const val SearchFieldTrailingIcon = "trailing icon of search text field"
}
package com.leverx.challenge.ui.components.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.leverx.challenge.R
import com.leverx.challenge.ui.environment.AppIcons
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset
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
    vm: SearchViewModel = hiltViewModel(),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    Search(
        uiState = uiState,
        onSearchClick = { query ->
            vm.fetchImages(query)
        },
    )
}

/**
 * Implementation of 'Search' UI component.
 */
@Composable
fun Search(
    uiState: UiState,
    onSearchClick: (String) -> Unit,
) {
    Column {
        SearchBar(
            onSearchClick = onSearchClick,
        )
        Spacer(modifier = Modifier.height(Offset.Halved))
        SearchContent(uiState)
    }
}


/**
 * Master 'Search' content UI component.
 * Represents UI under [SearchBar] in some [uiState].
 */
@Composable
private fun SearchContent(
    uiState: UiState,
) =
    when (uiState) {
        is UiState.Blank -> SearchContentBlank()
        is UiState.Loading -> SearchContentLoading()
        is UiState.Success -> SearchContentSuccess(uiState)
    }

/**
 * Implementation of 'Search' content UI component's [UiState.Blank] state.
 */
@Composable
private fun SearchContentBlank() {
    // nothing
}

/**
 * Implementation of 'Search' content UI component's [UiState.Loading] state.
 */
@Composable
private fun SearchContentLoading() {
    // nothing
}

/**
 * Implementation of 'Search' content UI component's [UiState.Success] state.
 */
@Composable
private fun SearchContentSuccess(uiState: UiState.Success) =
    LazyColumn {
        items(
            items = uiState.remoteImages.images ?: emptyList(),
            key = { it.id },
        ) { image ->
            Log.d("QWERTY", image.url ?: "null")
            AsyncImage(
                model = image.url,
                contentDescription = image.title,
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
@OptIn(ExperimentalMaterial3Api::class)
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
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
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
                onSearch = { onSearchClick(input) }
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
    ) {
        Icon(
            imageVector = AppIcons.Search,
            contentDescription = stringResource(R.string.cd_home_search_find)
        )
    }

// endregion
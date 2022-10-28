package com.leverx.challenge.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.leverx.challenge.R
import com.leverx.challenge.ui.environment.AppIcons
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset
import com.leverx.challenge.viewmodel.SearchViewModel

// region Previews

@Preview(
    showBackground = true,
)
@Composable
private fun Search_Preview() {
    AppTheme {
        Search(
            onSearchClick = {},
        )
    }
}

// endregion

/**
 * High-level master `Search` UI component.
 */
@Composable
fun Search(
    vm: SearchViewModel,
) =
    Search(
        onSearchClick = { query ->
            vm.fetchImages(query)
        },
    )

/**
 * Low-level primitivized implementation of 'Search' UI component.
 */
@Composable
fun Search(
    onSearchClick: (String) -> Unit,
) =
    Column {
        SearchBar(
            onSearchClick = onSearchClick,
        )
        Spacer(modifier = Modifier.height(Offset.Halved))
        LazyColumn {
            /* TODO: implement when item type is introduced */
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
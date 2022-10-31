package com.leverx.challenge.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.leverx.challenge.R
import com.leverx.challenge.ui.environment.AppIcons
import com.leverx.challenge.ui.environment.navigation.NavRoutes

// region Previews

@Composable
@Preview
private fun Home_Preview() {
    Home(
        currentRoute = NavRoutes.Search,
        navigateToSearch = {},
        navigateToHistory = {},
    ) {
        // navigation content
    }
}

// endregion

/**
 * Root component of all 'Home' destinations, wrapped into common UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar()
        },
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                navigateToSearch = navigateToSearch,
                navigateToHistory = navigateToHistory,
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}

// region App bar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() =
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            // empty
        }
    )

// endregion

// region Bottom bar

@Composable
private fun BottomBar(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
) =
    BottomAppBar {
        SearchItem(
            selected = (currentRoute == NavRoutes.Search),
            onClick = navigateToSearch,
        )
        HistoryItem(
            selected = (currentRoute == NavRoutes.History),
            onClick = navigateToHistory,
        )
    }

@Composable
private fun RowScope.SearchItem(
    selected: Boolean,
    onClick: () -> Unit,
) =
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = AppIcons.Search,
                contentDescription = stringResource(R.string.cd_home_search)
            )
        },
    )

@Composable
private fun RowScope.HistoryItem(
    selected: Boolean,
    onClick: () -> Unit,
) =
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = AppIcons.List,
                contentDescription = stringResource(R.string.cd_home_history)
            )
        },
    )

// endregion
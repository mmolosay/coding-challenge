package com.leverx.challenge.ui.components.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.leverx.challenge.R
import com.leverx.challenge.ui.components.common.PreviewStub
import com.leverx.challenge.ui.environment.AppIcons
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.composition.AppPreviewCompositionLocalProvider
import com.leverx.challenge.ui.environment.composition.PreviewWindowSizeClasses
import com.leverx.challenge.ui.environment.composition.WindowSize
import com.leverx.challenge.ui.environment.navigation.NavRoutes

// region Previews

@Composable
@Preview(
    showBackground = true,
    device = Devices.DEFAULT,
)
private fun Home_Compact_Preview() {
    AppPreviewCompositionLocalProvider(
        windowSizeClass = PreviewWindowSizeClasses.Phone,
    ) {
        AppTheme {
            HomeCompact(
                currentRoute = NavRoutes.Search,
                navigateToSearch = {},
                navigateToHistory = {},
                content = { PreviewStub("Navigation content") },
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    device = Devices.TABLET,
)
private fun Home_Medium_Preview() {
    AppPreviewCompositionLocalProvider(
        windowSizeClass = PreviewWindowSizeClasses.Tablet,
    ) {
        AppTheme {
            HomeMedium(
                currentRoute = NavRoutes.Search,
                navigateToSearch = {},
                navigateToHistory = {},
                content = { PreviewStub("Navigation content") },
            )
        }
    }
}

// endregion

/**
 * Root component of all 'Home' destinations, wrapped into common UI.
 */
@Composable
fun Home(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
    content: @Composable () -> Unit
) {
    when (WindowSize.widthClass) {
        WindowWidthSizeClass.Compact ->
            HomeCompact(
                currentRoute = currentRoute,
                navigateToSearch = navigateToSearch,
                navigateToHistory = navigateToHistory,
                content = content
            )
        else -> // Medium+
            HomeMedium(
                currentRoute = currentRoute,
                navigateToSearch = navigateToSearch,
                navigateToHistory = navigateToHistory,
                content = content
            )
    }
}

// region Home Compact

/**
 * Implementation of 'Home' UI component for [WindowWidthSizeClass.Compact] class.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeCompact(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
    content: @Composable () -> Unit
) =
    Scaffold(
        topBar = {
            AppBar()
        },
        bottomBar = {
            HorizontalNavigation(
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

/**
 * Navigation UI component, oriented horizontally.
 * Employs [NavigationBar].
 */
@Composable
private fun HorizontalNavigation(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
) =
    BottomAppBar {
        NavigationBarItem(
            selected = (currentRoute == NavRoutes.Search),
            onClick = navigateToSearch,
            icon = HomeTab.Search.icon,
            label = HomeTab.Search.label,
        )
        NavigationBarItem(
            selected = (currentRoute == NavRoutes.History),
            onClick = navigateToHistory,
            icon = HomeTab.History.icon,
            label = HomeTab.History.label,
        )
    }

// endregion

// region Home Medium

/**
 * Implementation of 'Home' UI component for [WindowWidthSizeClass.Medium] class.
 */
@Composable
private fun HomeMedium(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
    content: @Composable () -> Unit
) =
    Column {
        AppBar()
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            VerticalNavigation(
                currentRoute = currentRoute,
                navigateToSearch = navigateToSearch,
                navigateToHistory = navigateToHistory,
            )
            content()
        }
    }

/**
 * Navigation UI component, oriented vertically.
 * Employs [NavigationRail].
 */
@Composable
private fun VerticalNavigation(
    currentRoute: String?,
    navigateToSearch: () -> Unit,
    navigateToHistory: () -> Unit,
) =
    NavigationRail() {
        NavigationRailItem(
            selected = (currentRoute == NavRoutes.Search),
            onClick = navigateToSearch,
            icon = HomeTab.Search.icon,
            label = HomeTab.Search.label,
        )
        NavigationRailItem(
            selected = (currentRoute == NavRoutes.History),
            onClick = navigateToHistory,
            icon = HomeTab.History.icon,
            label = HomeTab.History.label,
        )
    }

// endregion

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

/**
 * Describes items of 'Home' UI component's navigation.
 * Provides unified components to be used in building different navigational UI.
 */
private enum class HomeTab(
    val label: @Composable () -> Unit,
    val icon: @Composable () -> Unit,
) {

    Search(
        label = {
            ItemLabel(
                textRes = R.string.home_tab_search,
            )
        },
        icon = {
            ItemIcon(
                imageVector = AppIcons.Search,
                contentDescriptionRes = R.string.cd_home_search,
            )
        },
    ),

    History(
        label = {
            ItemLabel(
                textRes = R.string.home_tab_history,
            )
        },
        icon = {
            ItemIcon(
                imageVector = AppIcons.List,
                contentDescriptionRes = R.string.cd_home_history,
            )
        }
    );

    companion object {

        /**
         * Unified label component for navigation items.
         */
        @Composable
        private fun ItemLabel(
            @StringRes textRes: Int,
        ) =
            Text(
                text = stringResource(textRes),
            )

        /**
         * Unified icon component for navigation items.
         */
        @Composable
        private fun ItemIcon(
            imageVector: ImageVector,
            @StringRes contentDescriptionRes: Int,
        ) =
            Icon(
                imageVector = imageVector,
                contentDescription = stringResource(contentDescriptionRes)
            )
    }
}
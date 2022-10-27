package com.leverx.challenge.ui.components.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.leverx.challenge.R
import com.leverx.challenge.ui.environment.AppTheme

// region Previews

@Preview(
    showBackground = true,
)
@Composable
private fun SettingsScreen_Preview() {
    AppTheme {
        SettingsScreen()
    }
}

// endregion

/**
 * High-level master component of 'Settings' screen UI.
 *
 * As all `Screen`s, wraps its component into navigational UI.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() =
    Scaffold(
        topBar = {
            AppBar()
        },
    ) {
        Settings(
            modifier = Modifier.padding(it),
        )
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() =
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.settings))
        },
        actions = {
            // empty
        },
    )
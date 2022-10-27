package com.leverx.challenge.ui.components.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset

// region Previews

@Preview(
    showBackground = true,
)
@Composable
private fun Settings_Preview() {
    AppTheme {
        Settings()
    }
}

// endregion

/**
 * Low-level primitivized implementation of 'Settings' UI component.
 */
@Composable
fun Settings(
    modifier: Modifier = Modifier,
) =
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            SettingsItem()
        }
    }

@Composable
private fun SettingsItem() =
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Offset.Regular,
                vertical = Offset.Halved
            ),
    ) {
        Text(text = "Nothing is here") // TODO: implement
    }
package com.leverx.challenge.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leverx.challenge.ui.environment.AppTheme
import com.leverx.challenge.ui.environment.Offset

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

@Composable
fun History() {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = Offset.Regular),
        verticalArrangement = Arrangement.spacedBy(Offset.Regular),
    ) {
        item {
            HistoryItem(query = "query1", images = listOf(Unit, Unit))
        }
        item {
            HistoryItem(query = "query2", images = listOf(Unit, Unit))
        }
    }
}

// TODO: add shimer for loading images
@Composable
private fun HistoryItem(
    query: String,
    images: List<Unit>, // TODO
) =
    Column {
        Text(text = query)
        Spacer(modifier = Modifier.height(Offset.Halved))
        Column(
            verticalArrangement = Arrangement.spacedBy(Offset.Halved),
        ) {
            images.forEach { image ->
                // TODO: layout
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(160.dp)
                        .background(LocalContentColor.current.copy(alpha = 0.08f))
                )
            }
        }
    }
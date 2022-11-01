package com.leverx.challenge.ui.components.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.hypot
import kotlin.math.sqrt

/**
 * Layout to be used instead of some `@Composable` 'content' in [Preview]s.
 */
@Composable
fun PreviewStub(
    description: String = "Content",
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TabbySurface()
        Text(
            text = description,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            color = LocalContentColor.current.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun TabbySurface(
    background: Color = LocalContentColor.current.copy(alpha = 0.08f),
    strokeWidth: Dp = TabbySurfaceStrokeWidth,
    strokeColor: Color = TabbySurfaceStrokeColor,
    space: Dp = TabbySurfaceSpace,
) =
    TabbySurface(
        background = background,
        strokeWidth = with(LocalDensity.current) { strokeWidth.toPx() },
        strokeColor = strokeColor,
        space = with(LocalDensity.current) { space.toPx() },
    )

@Composable
private fun TabbySurface(
    background: Color,
    strokeWidth: Float,
    strokeColor: Color,
    space: Float,
) =
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .clipToBounds()
    ) {
        val sin45deg = sqrt(2f) / 2
        val diagonal = hypot(size.width, size.height)
        val offset = (strokeWidth / 2) * sin45deg
        var point = 0f // current point on diagonal
        var position: Float
        while (point < diagonal) {
            position = point / sin45deg
            drawLine(
                color = strokeColor,
                start = Offset(position + offset, 0f - offset),
                end = Offset(0f - offset, position + offset),
                strokeWidth = strokeWidth,
            )
            // half of drawn stroke + space + half of next stroke
            point += strokeWidth + space
        }
    }

private val TabbySurfaceStrokeWidth = 32.dp
private val TabbySurfaceStrokeColor = Color(0x1A1647DA)
private val TabbySurfaceSpace = TabbySurfaceStrokeWidth
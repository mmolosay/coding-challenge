package com.leverx.challenge.ui.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun ImageDialog(
    onDismissRequest: () -> Unit,
    image: @Composable () -> Unit,
) =
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        image()
    }
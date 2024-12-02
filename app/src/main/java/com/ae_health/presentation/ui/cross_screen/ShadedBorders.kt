package com.ae_health.presentation.ui.cross_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun ShadedBorders(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Box(modifier = modifier.wrapContentSize()) {

        content()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TEXT_SPACING)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ExtendedTheme.extendedColors.background,
                            ExtendedTheme.extendedColors.surface
                        )
                    )
                )
                .align(Alignment.TopCenter)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TEXT_SPACING)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            ExtendedTheme.extendedColors.surface,
                            ExtendedTheme.extendedColors.background
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )
    }
}
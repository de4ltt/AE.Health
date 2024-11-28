package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.ui.theme.ExtendedTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits.FILTER_SMALL
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING

@Composable
fun Filter() {

}

/**
 * Small
 */
@Composable
fun FilterTileS(
    @StringRes title: Int,
    isActive: Boolean = false,
    onClick: () -> Unit
) {

    val barColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.primary else ExtendedTheme.extendedColors.secondaryContainer,
        label = ""
    )

    val textColor by animateColorAsState(
        if (isActive) ExtendedTheme.extendedColors.background else ExtendedTheme.extendedColors.secondary,
        label = ""
    )

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(Shapes.ICON_ROUNDED)
            .background(barColor),
        contentAlignment = Alignment.Center
    ) {
        RubikFontBasicText(
            text = stringResource(id = title),
            modifier = Modifier.padding(TEXT_SPACING),
            basicMarqueeEnabled = false,
            color = { textColor },
            style = TextStyle(
                fontSize = FILTER_SMALL,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * Large
 */
@Composable
fun FilterTileL() {

}
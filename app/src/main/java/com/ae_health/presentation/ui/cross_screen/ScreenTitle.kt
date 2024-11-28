package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun ScreenTitle(
    @StringRes titleRes: Int
) {

    RubikFontBasicText(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = titleRes),
        style = TextStyle(
            color = ExtendedTheme.extendedColors.secondary,
            fontWeight = FontWeight.SemiBold,
            fontSize = TextUnits.SCREEN_TITLE,
            textAlign = TextAlign.Center
        )
    )
}
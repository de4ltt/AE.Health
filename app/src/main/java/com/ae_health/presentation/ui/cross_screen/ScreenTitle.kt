package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        RubikFontBasicText(
            text = stringResource(id = titleRes),
            style = TextStyle(
                color = ExtendedTheme.extendedColors.secondary,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnits.SCREEN_TITLE,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun ScreenTitle(
    modifier: Modifier = Modifier,
    title: String
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        RubikFontBasicText(
            text = title,
            basicMarqueeEnabled = false,
            style = TextStyle(
                color = ExtendedTheme.extendedColors.secondary,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnits.SCREEN_TITLE,
                textAlign = TextAlign.Center
            )
        )
    }
}
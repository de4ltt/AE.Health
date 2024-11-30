package com.ae_health.presentation.ui.cross_screen.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun TitleText(
    @StringRes titleRes: Int
) {

    RubikFontBasicText(
        text = stringResource(id = titleRes),
        style = TextStyle(
            color = ExtendedTheme.extendedColors.secondary,
            fontWeight = FontWeight.SemiBold,
            fontSize = TextUnits.SECTION_TITLE
        ),
        basicMarqueeEnabled = false
    )
}
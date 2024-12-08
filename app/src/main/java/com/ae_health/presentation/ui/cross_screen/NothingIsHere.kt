package com.ae_health.presentation.ui.cross_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.BAR_TITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun NothingIsHere(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        RubikFontBasicText(
            text = stringResource(R.string.nothing_is_here),
            style = TextStyle(
                fontSize = BAR_TITLE,
                fontWeight = FontWeight.Normal,
                color = ExtendedTheme.extendedColors.primaryContainer
            )
        )
    }
}
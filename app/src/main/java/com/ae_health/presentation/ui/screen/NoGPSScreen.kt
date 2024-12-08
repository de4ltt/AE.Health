package com.ae_health.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.BAR_TITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.Dimens
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.theme.Orange
import com.ae_health.presentation.ui.theme.Red

@Composable
fun NoGPSScreen(
    modifier: Modifier = Modifier,
    permissionGranted: Boolean = true,
    locationEnabled: Boolean = true,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
        ) {

            val color by animateColorAsState(
                targetValue = when {
                    !permissionGranted -> Red
                    !locationEnabled -> Orange
                    else -> ExtendedTheme.extendedColors.primary
                },
                label = ""
            )

            val stringRes = when {
                !locationEnabled -> R.string.enable_location
                !permissionGranted -> R.string.grant_permission
                else -> R.string.looking_for_you
            }

            Icon(
                painter = painterResource(R.drawable.location),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(Dimens.ICON * 1.5f)
            )

            Crossfade(
                targetState = stringRes,
                label = ""
            ) {
                RubikFontBasicText(
                    text = stringResource(it),
                    basicMarqueeEnabled = false,
                    style = TextStyle(
                        color = ExtendedTheme.extendedColors.secondary,
                        fontSize = BAR_TITLE,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}
package com.ae_health.presentation.ui.screen.organization_info_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae_health.R
import com.ae_health.presentation.ui.cross_screen.util.AppButtonIcon
import com.ae_health.presentation.ui.cross_screen.util.AppButtonText
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun BottomButtons(
    modifier: Modifier,
    onHomeClick: () -> Unit,
    onRouteClick: () -> Unit,
    onPhoneClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_SPACING),
        verticalAlignment = Alignment.Top
    ) {

        val primaryColor = ExtendedTheme.extendedColors.primary
        val secondaryContainerColor = ExtendedTheme.extendedColors.secondaryContainer
        val backgroundColor = ExtendedTheme.extendedColors.background

        AppButtonIcon(
            color = { secondaryContainerColor },
            iconColor = { primaryColor },
            icon = R.drawable.home,
            onClick = onHomeClick
        )

        AppButtonText(
            modifier = Modifier.weight(1f),
            color = { primaryColor },
            titleColor = { backgroundColor },
            title = R.string.route,
            onClick = onRouteClick
        )

        AppButtonIcon(
            color = { secondaryContainerColor },
            iconColor = { primaryColor },
            icon = R.drawable.phone,
            onClick = onPhoneClick
        )
    }
}
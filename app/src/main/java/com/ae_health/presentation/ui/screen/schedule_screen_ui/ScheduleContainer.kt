package com.ae_health.presentation.ui.screen.schedule_screen_ui

import androidx.compose.animation.core.EaseInOutQuart
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.ui.theme.Dimens
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun ScheduleContainer(
    modifier: Modifier = Modifier.Companion,
    content: @Composable () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
    ) {

        var _boxHeight by remember {
            mutableStateOf(0.dp)
        }

        val boxHeight by animateDpAsState(_boxHeight, label = "", animationSpec = tween(1000, easing = EaseInOutQuart))

        val localDensity = LocalDensity.current

        Box(
            modifier = Modifier.Companion
                .fillMaxWidth(0.01f)
                .height(boxHeight)
                .background(ExtendedTheme.extendedColors.primaryContainer)
        )

        Box(
            modifier = Modifier.Companion
                .clip(Shapes.SCHEDULE_CARD_ROUNDED)
                .fillMaxWidth(0.99f)
                .wrapContentHeight()
                .background(ExtendedTheme.extendedColors.secondaryContainer)
                .onPlaced {
                    _boxHeight = with(localDensity) { it.size.height.toDp() }
                }
        ) {

            Column(
                modifier = Modifier.Companion
                    .padding(Dimens.ICON_PADDING)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.MEDIUM_SPACING),
                horizontalAlignment = Alignment.Companion.Start
            ) {

                content()
            }
        }
    }
}
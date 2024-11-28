package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON_PADDING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.transport.ui.util.bounceClick


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    curDestination: ScreenDestinations,
    navigate: (ScreenUIEvent) -> Unit
) {

    val entries = ScreenDestinations.entries.toList()

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(ExtendedTheme.extendedColors.surface),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(entries) {
            it.bottomIcon?.let { icon ->
                BottomBarIcon(
                    isActive = it == curDestination,
                    icon = icon,
                    onClick = { navigate(ScreenUIEvent.ChangeCurrentDestination(it.route)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    isActive: Boolean = false,
    onClick: () -> Unit
) {

    val iconColor by animateColorAsState(
        targetValue = if (isActive) ExtendedTheme.extendedColors.background else ExtendedTheme.extendedColors.secondary,
        label = ""
    )

    val barColor by animateColorAsState(
        targetValue = if (isActive) ExtendedTheme.extendedColors.primary else ExtendedTheme.extendedColors.background,
        label = ""
    )

    Box(
        modifier = modifier
            .fillMaxHeight(0.08f)
            .wrapContentWidth()
            .clip(Shapes.BOTTOM_ICON_ROUNDED)
            .background(barColor)
            .bounceClick(
                enabled = !isActive,
                onClick = { onClick() }
            ),
        contentAlignment = Alignment.TopCenter
    ) {

        Icon(
            modifier = Modifier
                .padding(
                    top = SMALL_ICON_PADDING,
                    start = SMALL_ICON_PADDING,
                    end = SMALL_ICON_PADDING
                )
                .size(SMALL_ICON),
            painter = painterResource(id = icon),
            tint = { iconColor },
            contentDescription = null
        )
    }
}
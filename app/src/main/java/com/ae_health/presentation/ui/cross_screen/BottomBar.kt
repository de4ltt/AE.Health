package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON_PADDING
import com.ae_health.presentation.ui.theme.ExtendedTheme


@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    isActive: Boolean = false
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
            .fillMaxHeight()
            .wrapContentWidth()
            .clip(Shapes.BOTTOM_ICON_ROUNDED)
            .background(barColor),
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
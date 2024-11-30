package com.ae_health.presentation.ui.cross_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON
import com.ae_health.presentation.ui.theme.Dimens.SMALL_ICON_PADDING
import com.transport.ui.util.bounceClick

@Composable
fun AppButtonText(
    modifier: Modifier = Modifier,
    color: ColorProducer,
    titleColor: ColorProducer,
    @StringRes title: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(Shapes.ICON_ROUNDED)
            .background(color.invoke())
            .bounceClick { onClick() },
        contentAlignment = Alignment.Center
    ) {

        RubikFontBasicText(
            text = stringResource(id = title),
            modifier = Modifier.padding(DEFAULT_SPACING),
            basicMarqueeEnabled = false,
            color = titleColor,
            style = TextStyle(
                fontSize = TextUnits.SEARCH,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppButtonIcon(
    modifier: Modifier = Modifier,
    color: ColorProducer,
    iconColor: ColorProducer,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .wrapContentWidth()
            .clip(Shapes.ICON_ROUNDED)
            .background(color.invoke())
            .bounceClick(
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
            tint = iconColor,
            contentDescription = null
        )
    }
}
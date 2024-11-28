package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.ICON
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.theme.Red

enum class OrganizationType(
    @DrawableRes val icon: Int? = null,
    @StringRes val typeName: Int? = null
) {
    UNKNOWN,
    PHARMACY,
    HOSPITAL,
    POLYCLINIC,
    SPA
}

@Composable
fun OrganizationBar(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {

    val organizationType = OrganizationType.UNKNOWN

    Row(
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
    ) {

        OrganizationBarIcon(organizationType = organizationType)

        OrganizationBarText(title = title, subtitle = subtitle)
    }
}

@Composable
fun OrganizationBarIcon(
    modifier: Modifier = Modifier,
    organizationType: OrganizationType = OrganizationType.UNKNOWN
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(Shapes.ICON_ROUNDED)
            .size(ICON)
            .background(color = Red),
        contentAlignment = Alignment.Center
    ) {
        organizationType.icon?.let {

            Icon(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize(),
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
    }
}

@Composable
fun OrganizationBarText(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null
) {

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(TEXT_SPACING)
    ) {

        RubikFontBasicText(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = TextUnits.BAR_TITLE,
                color = ExtendedTheme.extendedColors.secondary
            )
        )

        subtitle?.let {
            RubikFontBasicText(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = TextUnits.BAR_SUBTITLE,
                    color = ExtendedTheme.extendedColors.primaryContainer
                )
            )
        }
    }
}
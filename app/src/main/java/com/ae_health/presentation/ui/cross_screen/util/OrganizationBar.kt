package com.ae_health.presentation.ui.cross_screen.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ae_health.R
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.ICON
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.transport.ui.util.bounceClick

enum class OrganizationType(
    @DrawableRes val icon: Int = R.drawable.pharmacy,
    @StringRes val typeName: Int = R.string.medical_institution
) {
    DEFAULT,
    PHARMACY,
    HOSPITAL(icon = R.drawable.hospital),
    POLYCLINIC(icon = R.drawable.hospital),
    SPA(icon = R.drawable.cocktail)
}

@Composable
fun OrganizationBar(
    modifier: Modifier = Modifier,
    organization: Organization,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .bounceClick { onClick() },
        horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OrganizationBarIcon(organizationType = organization.type)

        OrganizationBarText(
            title = organization.title,
            subtitle = stringResource(organization.type.typeName)
        )
    }
}

@Composable
private fun OrganizationBarIcon(
    modifier: Modifier = Modifier,
    organizationType: OrganizationType = OrganizationType.DEFAULT
) {

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(Shapes.ICON_ROUNDED)
            .size(ICON)
            .aspectRatio(1f)
            .background(color = ExtendedTheme.extendedColors.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        organizationType.icon.let {

            Icon(
                modifier = Modifier.padding(15.dp),
                painter = painterResource(id = it),
                contentDescription = null,
                tint = ExtendedTheme.extendedColors.primary
            )
        }
    }
}

@Composable
private fun OrganizationBarText(
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
                text = it,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = TextUnits.BAR_SUBTITLE,
                    color = ExtendedTheme.extendedColors.primaryContainer
                )
            )
        }
    }
}
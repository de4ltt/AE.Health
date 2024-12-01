package com.ae_health.presentation.ui.cross_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.util.TitleText
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING

@Composable
fun TitledOrganizations(
    modifier: Modifier = Modifier,
    organizations: List<Organization>,
    @StringRes title: Int,
    onClick: (Organization) -> Unit,
    onHold: (Organization) -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
        horizontalAlignment = Alignment.Start
    ) {

        TitleText(title)

        LazyOrganizationsList(
            organizations = organizations,
            onClick = onClick,
            onHold = onHold
        )
    }
}
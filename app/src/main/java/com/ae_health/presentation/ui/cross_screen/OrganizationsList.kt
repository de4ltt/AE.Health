package com.ae_health.presentation.ui.cross_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.util.OrganizationBar
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING

@Composable
fun LazyOrganizationsList(
    modifier: Modifier = Modifier,
    organizations: List<Organization>,
    onClick: (Organization) -> Unit,
    onHold: (Organization) -> Unit = {}
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING),
        horizontalAlignment = Alignment.Start
    ) {

        items(organizations) {

            OrganizationBar(
                modifier = Modifier.fillMaxWidth(),
                organization = it,
                onClick = { onClick(it) },
                onHold = { onHold(it) }
            )
        }
    }
}

@Composable
fun OrganizationsList(
    organizations: List<Organization>,
    onClick: (Organization) -> Unit,
    onHold: (Organization) -> Unit = {}
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING),
        horizontalAlignment = Alignment.Start
    ) {

        organizations.forEach() {

            OrganizationBar(
                modifier = Modifier.fillMaxWidth(),
                organization = it,
                onClick = { onClick(it) },
                onHold = { onHold(it) }
            )
        }
    }
}
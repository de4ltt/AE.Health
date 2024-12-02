package com.ae_health.presentation.ui.cross_screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.util.OrganizationBar
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import com.ae_health.presentation.ui.theme.Dimens.TEXT_SPACING

@Composable
fun LazyOrganizationsList(
    modifier: Modifier = Modifier,
    organizations: List<Organization>,
    onClick: (Organization) -> Unit,
    onHold: (Organization) -> Unit = {}
) {

    val state = rememberLazyListState()

    val firstVisibleItemIndex by remember { derivedStateOf { state.firstVisibleItemIndex } }

    ShadedBorders {

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING),
            horizontalAlignment = Alignment.Start,
            state = state,
            contentPadding = PaddingValues(vertical = TEXT_SPACING)
        ) {

            itemsIndexed(organizations) { index, it ->

                OrganizationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(
                            fadeInSpec = tween(100 * (index - firstVisibleItemIndex)),
                            fadeOutSpec = tween(100 * (index - firstVisibleItemIndex))
                        )
                        .graphicsLayer {

                        },
                    organization = it,
                    onClick = { onClick(it) },
                    onHold = { onHold(it) }
                )
            }
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
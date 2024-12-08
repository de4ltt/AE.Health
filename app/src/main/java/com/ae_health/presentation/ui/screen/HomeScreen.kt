package com.ae_health.presentation.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.R
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.cross_screen.NothingIsHere
import com.ae_health.presentation.ui.cross_screen.TitledOrganizations

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    val latestVisited = state.latestVisitedOrganizations.takeLast(7)
    val bestNearby = state.curBestOrganizations.takeLast(7)
    val foundOrganizations = state.foundOrganizations

    val isSearchActive = state.isSearchActive

    val isLatestShown =
        latestVisited.isNotEmpty()

    val organizations = when {
        isSearchActive && foundOrganizations.isNotEmpty() -> foundOrganizations
        isLatestShown -> latestVisited
        else -> bestNearby
    }

    val title = when {
        isSearchActive && foundOrganizations.isNotEmpty() -> R.string.here_found
        isLatestShown -> R.string.latest_visited
        else -> R.string.best_nearby
    }

    Column {

        AnimatedContent(
            targetState = organizations,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) },
            label = ""
        ) {

            if (it.isNotEmpty())
                TitledOrganizations(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    organizations = organizations,
                    title = title,
                    onClick = { onEvent(ScreenUIEvent.ShowOrganization(it)) },
                    onHold = { onEvent(ScreenUIEvent.SwitchFavAppointBar(it)) }
                )
            else NothingIsHere(modifier = Modifier.fillMaxSize())
        }
    }
}
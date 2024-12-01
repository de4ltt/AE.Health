package com.ae_health.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.R
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.cross_screen.TitledOrganizations
import java.time.LocalTime
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    val latestVisited = state.latestVisitedOrganizations
    val bestNearby = state.curBestOrganizations

    val isLatestShown =
        latestVisited.isNotEmpty() && Random(LocalTime.now().toSecondOfDay()).nextInt(0, 2) == 1

    val organizations = if (isLatestShown) latestVisited else bestNearby
    val title = if (isLatestShown) R.string.latest_visited else R.string.best_nearby

    Column {

        AnimatedVisibility(
            visible = !state.isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            TitledOrganizations(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                organizations = organizations,
                title = title,
                onClick = { onEvent(ScreenUIEvent.ShowOrganization(it)) },
                onHold = { onEvent(ScreenUIEvent.SwitchFavAppointBar(it)) }
            )
        }
    }
}
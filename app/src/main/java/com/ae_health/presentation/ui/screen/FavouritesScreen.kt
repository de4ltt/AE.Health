package com.ae_health.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.cross_screen.LazyOrganizationsList
import com.ae_health.presentation.ui.cross_screen.NothingIsHere
import com.ae_health.presentation.ui.cross_screen.ShadedBorders

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    Crossfade(state.favouriteOrganizations) {

        if (it.isNotEmpty())
            ShadedBorders {
                LazyOrganizationsList(
                    modifier = modifier,
                    organizations = it,
                    onClick = { onEvent(ScreenUIEvent.ShowOrganization(it)) },
                    onHold = { onEvent(ScreenUIEvent.SwitchFavAppointBar(it)) }
                )
            }
        else NothingIsHere(modifier = Modifier.fillMaxSize())
    }

}
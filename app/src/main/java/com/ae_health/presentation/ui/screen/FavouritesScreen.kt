package com.ae_health.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.cross_screen.LazyOrganizationsList

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    LazyOrganizationsList(
        modifier = modifier,
        organizations = state.favouriteOrganizations,
        onClick = { onEvent(ScreenUIEvent.ShowOrganization(it)) }
    )
}
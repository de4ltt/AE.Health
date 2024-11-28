package com.ae_health.presentation.ui.cross_screen

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations

@Composable
fun TopBar(
    destination: ScreenDestinations,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    Crossfade(
        targetState = destination,
        label = ""
    ) {
        when (it) {
            ScreenDestinations.HOME -> {
                SearchBar(
                    textValue = state.searchBarInput,
                    onValueChange = { input -> onEvent(ScreenUIEvent.ChangeSearchInput(input)) }
                )
            }

            else -> {
                it.titleRes?.let { title -> ScreenTitle(titleRes = title) }
            }
        }
    }
}
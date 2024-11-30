package com.ae_health.presentation.ui.cross_screen.bar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.Filter
import com.ae_health.presentation.ui.cross_screen.ScreenTitle
import com.ae_health.presentation.ui.cross_screen.SearchBar
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING

@Composable
fun TopBar(
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    Crossfade(
        targetState = state.curDestination,
        label = ""
    ) {
        when (it) {
            ScreenDestinations.HOME -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
                ) {
                    SearchBar(
                        modifier = Modifier.padding(
                            start = DEFAULT_SPACING,
                            end = DEFAULT_SPACING
                        ),
                        textValue = state.searchBarInput,
                        onValueChange = { input -> onEvent(ScreenUIEvent.ChangeSearchInput(input)) }
                    )

                    Filter(
                        state = state,
                        onClick = { onEvent(ScreenUIEvent.SwitchFilter(it)) }
                    )
                }
            }

            else -> {
                it.titleRes?.let { title -> ScreenTitle(titleRes = title) }
            }
        }
    }
}
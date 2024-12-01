package com.ae_health.presentation.ui.cross_screen.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.Filter
import com.ae_health.presentation.ui.cross_screen.IconTextField
import com.ae_health.presentation.ui.cross_screen.ScreenTitle
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {

    if (state.curDestination == ScreenDestinations.HOME) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
        ) {
            IconTextField(
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
    } else {
        state.curDestination.titleRes?.let { title ->
            ScreenTitle(
                titleRes = title
            )
        }
    }
}
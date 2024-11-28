package com.ae_health.presentation.ui.cross_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.asScreenDestinations
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.TOP_PADDING

@Composable
fun DefaultScaffold(
    modifier: Modifier = Modifier,
    screenUIState: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit,
    content: @Composable (Modifier) -> Unit
) {

    Column(
        modifier = modifier.padding(
            top = TOP_PADDING,
            start = DEFAULT_SPACING,
            end = DEFAULT_SPACING
        )
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            TopBar(
                destination = screenUIState.curDestination.asScreenDestinations,
                state = screenUIState,
                onEvent = onEvent
            )

            content(Modifier.weight(1f))

        }

        BottomBar(
            navigate = onEvent,
            curRoute = screenUIState.curDestination
        )
    }
}
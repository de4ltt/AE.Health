package com.ae_health.presentation.ui.cross_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.cross_screen.bar.BottomBar
import com.ae_health.presentation.ui.cross_screen.bar.TopBar
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
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
        )
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
        ) {

            TopBar(
                state = screenUIState,
                onEvent = onEvent
            )

            content(
                Modifier
                    .weight(1f)
                    .padding(DEFAULT_SPACING)
            )

        }

        BottomBar(
            onEvent = onEvent,
            curDestination = screenUIState.curDestination
        )
    }
}
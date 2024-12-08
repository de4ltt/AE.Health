package com.ae_health.presentation.ui.cross_screen.bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.Filter
import com.ae_health.presentation.ui.cross_screen.ScreenTitle
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onEvent: (ScreenUIEvent) -> Unit
) {


    SharedTransitionLayout {
        AnimatedContent(
            modifier = Modifier.skipToLookaheadSize(),
            targetState = state.curDestination, label = "topbar_crossfade",
            transitionSpec = { fadeIn(tween(200)).togetherWith(fadeOut(tween(200))) }
        ) {
            if (it == ScreenDestinations.HOME) {
                Column(
                    modifier = modifier
                        .skipToLookaheadSize(),
                    verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
                ) {
                    /*IconTextField(
                    modifier = Modifier.padding(
                        start = DEFAULT_SPACING,
                        end = DEFAULT_SPACING
                    ),
                    textValue = state.searchBarInput,
                    onValueChange = { input -> onEvent(ScreenUIEvent.ChangeSearchInput(input)) }
                )*/

                    Filter(
                        modifier = Modifier.skipToLookaheadSize(),
                        state = state,
                        onSearch = { onEvent(ScreenUIEvent.SearchForOrganizations) },
                        onClick = { onEvent(ScreenUIEvent.SwitchFilter(it)) }
                    )
                }
            } else {
                it.titleRes?.let { title ->
                    ScreenTitle(
                        modifier = Modifier.skipToLookaheadSize(),
                        titleRes = title
                    )
                }
            }
        }
    }
}
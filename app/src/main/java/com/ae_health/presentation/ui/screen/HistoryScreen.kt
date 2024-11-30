package com.ae_health.presentation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.ui.screen.history_screen_ui.DatedHistory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    state: ScreenUIState,
    onShowOrganization: (Organization) -> Unit
) {

    DatedHistory(
        history = state.historyOfVisitedOrganizations,
        onShowOrganization = onShowOrganization
    )
}
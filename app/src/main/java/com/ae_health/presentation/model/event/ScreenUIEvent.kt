package com.ae_health.presentation.model.event

import com.ae_health.presentation.model.util.ScreenDestinations

sealed class ScreenUIEvent {

    data class ChangeSearchInput(val input: String): ScreenUIEvent()

    data class ChangeCurrentDestination(val destination: ScreenDestinations): ScreenUIEvent()
}

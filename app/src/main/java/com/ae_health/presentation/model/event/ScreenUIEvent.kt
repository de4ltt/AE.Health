package com.ae_health.presentation.model.event

sealed class ScreenUIEvent {

    data class ChangeSearchInput(val input: String): ScreenUIEvent()

    data class ChangeCurrentDestination(val destination: Routes): ScreenUIEvent()
}

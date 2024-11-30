package com.ae_health.presentation.model.event

import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations

sealed class ScreenUIEvent {

    data class ChangeSearchInput(val input: String): ScreenUIEvent()

    data class ChangeCurrentDestination(val destination: ScreenDestinations): ScreenUIEvent()

    data class SwitchFilter(val filter: Filter?): ScreenUIEvent()

    data class ShowOrganization(val organization: Organization): ScreenUIEvent()
    data object IdleShowOrganization: ScreenUIEvent()
}

package com.ae_health.presentation.model.event

import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations

sealed class ScreenUIEvent {

    data class ChangeSearchInput(val input: String): ScreenUIEvent()

    data class ChangeCurrentDestination(val destination: ScreenDestinations): ScreenUIEvent()

    data class SwitchFilter(val filter: Filter?): ScreenUIEvent()

    data class ShowOrganization(val organization: Organization): ScreenUIEvent()
    data object IdleShowOrganization: ScreenUIEvent()

    data object SwitchAddAppointment: ScreenUIEvent()
    data class AddAppointment(val appointment: Appointment): ScreenUIEvent()
    data class DeleteAppointment(val appointment: Appointment): ScreenUIEvent()

    data class AddFavourite(val organization: Organization): ScreenUIEvent()
    data class DeleteFavourite(val organization: Organization): ScreenUIEvent()

    data class SwitchFavAppointBar(val organization: Organization): ScreenUIEvent()
    data object IdleSwitchFavAppointBar: ScreenUIEvent()

}

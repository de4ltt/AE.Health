package com.ae_health.presentation.ui.screen.schedule_screen_ui

import androidx.compose.runtime.Composable
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.util.OrganizationBar

@Composable
fun ScheduleCard(
    appointment: Appointment,
    showOrganization: (Organization) -> Unit
) {

    ScheduleContainer {

        ScheduleNoteDataTime(appointment.dateTime)

        OrganizationBar(
            organization = appointment.organization,
            onClick = { showOrganization(appointment.organization) }
        )

        appointment.organization.address?.let {
            ScheduleNoteInfoText(infoType = InfoType.ADDRESS, text = it)
        }

        appointment.room?.let {
            ScheduleNoteInfoText(infoType = InfoType.ROOM, text = it)
        }

        appointment.specialist?.let {
            ScheduleNoteInfoText(infoType = InfoType.SPECIALIST, text = it)
        }

        appointment.comment?.let {
            ScheduleNoteInfoText(infoType = InfoType.COMMENT, text = it)
        }
    }
}

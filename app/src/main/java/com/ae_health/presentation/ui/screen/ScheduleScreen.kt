package com.ae_health.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.state.randomOrganization
import com.ae_health.presentation.ui.screen.schedule_screen_ui.ScheduleCard
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    appointments: List<Appointment> = listOf(
        Appointment(
            dateTime = LocalDateTime.of(LocalDate.of(2024, 12, 23), LocalTime.of(14, 23)),
            organization = randomOrganization[0],
            room = "453",
            specialist = "Евтухов А.П.",
            comment = "Взаять с собой еды"
        ),
        Appointment(
            dateTime = LocalDateTime.of(LocalDate.of(2024, 12, 23), LocalTime.of(14, 23)),
            organization = randomOrganization[0],
            room = "453",
            specialist = "Евтухов А.П.",
            comment = "Взаять с собой еды"
        ),
        Appointment(
            dateTime = LocalDateTime.of(LocalDate.of(2024, 12, 23), LocalTime.of(14, 23)),
            organization = randomOrganization[0],
            room = "453",
            specialist = "Евтухов А.П.",
            comment = "Взаять с собой еды"
        )
    ),
    showOrganization: (Organization) -> Unit
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
        ) {
            items(appointments) {
                ScheduleCard(it) { org ->
                    showOrganization(org)
                }
            }
        }

        /*FloatingActionButton(
            onClick = showAddAppointment,
            modifier = Modifier
                .wrapContentSize()
                .clip(ICON_ROUNDED)
                .align(Alignment.BottomEnd),
            shape = RectangleShape,
            containerColor = ExtendedTheme.extendedColors.primary,
        ) {

            val background = ExtendedTheme.extendedColors.background

            Icon(
                modifier = Modifier.padding(SMALL_ICON_PADDING),
                painter = painterResource(id = R.drawable.plus),
                tint = { background },
                contentDescription = null
            )
        }*/
    }
}

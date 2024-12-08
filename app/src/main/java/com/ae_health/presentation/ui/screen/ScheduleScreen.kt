package com.ae_health.presentation.ui.screen

import androidx.compose.animation.Crossfade
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
import com.ae_health.presentation.ui.cross_screen.NothingIsHere
import com.ae_health.presentation.ui.screen.schedule_screen_ui.ScheduleCard
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    appointments: List<Appointment>,
    showOrganization: (Organization) -> Unit
) {

    Crossfade(appointments.isNotEmpty()) {

        if (it)
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
            }
        else NothingIsHere(modifier = Modifier.fillMaxSize())
    }
}

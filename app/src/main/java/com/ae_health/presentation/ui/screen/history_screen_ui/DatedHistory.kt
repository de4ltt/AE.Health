package com.ae_health.presentation.ui.screen.history_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.OrganizationsList
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import java.time.LocalDate

@Composable
fun DatedHistory(
    history: Map<LocalDate, List<Organization>>,
    onShowOrganization: (Organization) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
    ) {

        history.forEach { date, orgList ->

            item {

                Column(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
                ) {

                    DateDisplayText(date)

                    OrganizationsList(
                        organizations = orgList,
                        onClick = { onShowOrganization(it) }
                    )
                }
            }
        }
    }
}
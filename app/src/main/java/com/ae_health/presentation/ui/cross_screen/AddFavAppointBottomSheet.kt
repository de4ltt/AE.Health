package com.ae_health.presentation.ui.cross_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.theme.Shapes.ICON_ROUNDED
import com.ae_health.presentation.theme.TextUnits
import com.ae_health.presentation.ui.cross_screen.util.AppButtonText
import com.ae_health.presentation.ui.cross_screen.util.OrganizationBar
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.screen.schedule_screen_ui.InfoType
import com.ae_health.presentation.ui.theme.Dimens
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.LARGE_SPACING
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.util.stringRes
import com.transport.ui.util.bounceClick
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFavAppointBottomSheet(
    modifier: Modifier = Modifier,
    organization: Organization? = null,
    onBack: () -> Unit,
    onAddFavourite: (Organization) -> Unit,
    onAddAppointment: (Appointment) -> Unit
) {

    organization?.let {

        var isAddAppointment: Boolean by remember {
            mutableStateOf(false)
        }

        BackHandler { onBack() }

        ModalBottomSheet(
            dragHandle = {},
            modifier = modifier,
            onDismissRequest = onBack,
            containerColor = ExtendedTheme.extendedColors.background
        ) {
            Crossfade(isAddAppointment) { st ->

                if (!st)
                    Column(
                        modifier = Modifier
                            .padding(LARGE_SPACING * 1.25f)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        RubikFontBasicText(
                            modifier = Modifier.bounceClick { onAddFavourite(it) },
                            text = stringResource(R.string.add_to_fav),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = TextUnits.BAR_TITLE,
                                color = ExtendedTheme.extendedColors.primary
                            )
                        )

                        RubikFontBasicText(
                            modifier = Modifier.bounceClick { isAddAppointment = true },
                            text = stringResource(R.string.add_appointment),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = TextUnits.BAR_TITLE,
                                color = ExtendedTheme.extendedColors.primary
                            )
                        )
                    }
                else
                    Column(
                        modifier = Modifier
                            .padding(LARGE_SPACING * 1.25f)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(DEFAULT_SPACING),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        var appointment by remember {
                            mutableStateOf(
                                Appointment(
                                    dateTime = LocalDateTime.now().plusDays(1),
                                    organization = it
                                )
                            )
                        }

                        var isDateTimePickerVisible by remember {
                            mutableStateOf(false)
                        }

                        val time = appointment.dateTime.toLocalTime()
                        val date = appointment.dateTime.toLocalDate()
                        val curYear = LocalDate.now().year
                        val curDate = "${date.dayOfMonth} ${stringResource(date.month.stringRes)}"
                        val curDateWithYear = "$curDate ${date.year}"

                        val value = when {
                            date.year == curYear -> curDate
                            else -> curDateWithYear
                        } + " " + stringResource(R.string.at_time).lowercase() + " ${time.hour}:${time.minute}"

                        OrganizationBar(
                            organization = appointment.organization,
                            onClick = {}
                        )

                        DatePickerBar(
                            textValue = value
                        ) {
                            isDateTimePickerVisible = true
                        }

                        IconTextField(
                            textValue = appointment.room ?: "",
                            onEmptyValue = InfoType.ROOM.titleRes
                        ) { newValue ->
                            appointment = appointment.copy(
                                room = if (newValue.isNotEmpty()) newValue else null
                            )
                        }

                        IconTextField(
                            textValue = appointment.specialist ?: "",
                            onEmptyValue = InfoType.SPECIALIST.titleRes
                        ) { newValue ->
                            appointment = appointment.copy(
                                specialist = if (newValue.isNotEmpty()) newValue else null
                            )
                        }

                        IconTextField(
                            textValue = appointment.comment ?: "",
                            onEmptyValue = InfoType.COMMENT.titleRes
                        ) { newValue ->
                            appointment = appointment.copy(
                                comment = if (newValue.isNotEmpty()) newValue else null
                            )
                        }

                        val primary = ExtendedTheme.extendedColors.primary
                        val background = ExtendedTheme.extendedColors.background

                        AppButtonText(
                            color = { primary },
                            titleColor = { background },
                            title = R.string.save,
                            onClick = { onAddAppointment(appointment) }
                        )

                        DateTimePicker(
                            visible = isDateTimePickerVisible
                        ) { dateTime ->
                            appointment = appointment.copy(
                                dateTime = dateTime
                            )
                            isDateTimePickerVisible = false
                        }
                    }
            }
        }
    }
}

@Composable
private fun DatePickerBar(
    modifier: Modifier = Modifier,
    textValue: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(ICON_ROUNDED)
            .background(ExtendedTheme.extendedColors.secondaryContainer),
    ) {

        Row(
            modifier = Modifier
                .padding(Dimens.FIELD_PADDING)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onClick() }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DEFAULT_SPACING)
        ) {

            val iconTint = ExtendedTheme.extendedColors.primaryContainer

            Icon(
                modifier = Modifier.size(Dimens.SMALL_ICON),
                painter = painterResource(id = R.drawable.clock),
                tint = iconTint,
                contentDescription = null,
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {

                RubikFontBasicText(
                    text = textValue,
                    style = TextStyle(
                        color = ExtendedTheme.extendedColors.primaryContainer,
                        fontSize = TextUnits.SEARCH,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
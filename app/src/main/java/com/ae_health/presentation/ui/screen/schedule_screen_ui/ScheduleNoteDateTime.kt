package com.ae_health.presentation.ui.screen.schedule_screen_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.ae_health.R
import com.ae_health.presentation.theme.TextUnits.SCREEN_TITLE
import com.ae_health.presentation.ui.cross_screen.util.RubikFontBasicText
import com.ae_health.presentation.ui.theme.ExtendedTheme
import com.ae_health.presentation.ui.util.stringRes
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ScheduleNoteDataTime(
    dateTime: LocalDateTime
) {

    val time = dateTime.toLocalTime()
    val date = dateTime.toLocalDate()
    val curYear = LocalDate.now().year
    val curDate = "${date.dayOfMonth} ${stringResource(date.month.stringRes)}"
    val curDateWithYear = "$curDate ${date.year}"

    val value = when {
        date.year == curYear -> curDate
        else -> curDateWithYear
    } + " " + stringResource(R.string.at_time).lowercase() + " ${time.hour}:${time.minute}"

    val color = ExtendedTheme.extendedColors.primary

    RubikFontBasicText(
        text = value,
        basicMarqueeEnabled = false,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = SCREEN_TITLE
        ),
        color = { color }
    )
}
package com.ae_health.presentation.ui.cross_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar

@Composable
private fun DatePickerDialog(
    initialDate: LocalDate,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, initialDate.year)
        set(Calendar.MONTH, initialDate.monthValue - 1)
        set(Calendar.DAY_OF_MONTH, initialDate.dayOfMonth)
    }

    DisposableEffect(Unit) {
        val datePickerDialog = android.app.DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.setOnCancelListener { onDismissRequest() }
        datePickerDialog.show()

        onDispose { datePickerDialog.dismiss() }
    }
}

@Composable
private fun TimePickerDialog(
    initialTime: LocalTime,
    onDismissRequest: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, initialTime.hour)
        set(Calendar.MINUTE, initialTime.minute)
    }

    DisposableEffect(Unit) {
        val timePickerDialog = android.app.TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                onTimeSelected(LocalTime.of(hourOfDay, minute))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.setOnCancelListener { onDismissRequest() }
        timePickerDialog.show()

        onDispose { timePickerDialog.dismiss() }
    }
}

@Composable
fun DateTimePicker(
    visible: Boolean = false,
    onSetDateTime: (LocalDateTime) -> Unit
) {
    var date by remember { mutableStateOf(LocalDate.now().plusDays(1)) }
    var time by remember { mutableStateOf(LocalTime.now()) }
    var isDateSet by remember { mutableStateOf(false) }

    if (visible) {
        if (!isDateSet) {
            DatePickerDialog(
                initialDate = date,
                onDismissRequest = { isDateSet = false },
                onDateSelected = { selectedDate ->
                    date = selectedDate
                    isDateSet = true
                }
            )
        } else {
            TimePickerDialog(
                initialTime = time,
                onDismissRequest = { isDateSet = false },
                onTimeSelected = { selectedTime ->
                    time = selectedTime
                    onSetDateTime(LocalDateTime.of(date, time))
                    isDateSet = false
                }
            )
        }
    }
}
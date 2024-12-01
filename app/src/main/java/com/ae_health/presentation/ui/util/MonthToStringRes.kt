package com.ae_health.presentation.ui.util

import androidx.annotation.StringRes
import com.ae_health.R
import java.time.Month

val Month.stringRes
    @StringRes
    get(): Int = when (this) {
        Month.JANUARY -> R.string.jan
        Month.FEBRUARY -> R.string.feb
        Month.MARCH -> R.string.mar
        Month.APRIL -> R.string.apr
        Month.MAY -> R.string.may
        Month.JUNE -> R.string.jun
        Month.JULY -> R.string.jul
        Month.AUGUST -> R.string.aug
        Month.SEPTEMBER -> R.string.sep
        Month.OCTOBER -> R.string.oct
        Month.NOVEMBER -> R.string.nov
        Month.DECEMBER -> R.string.dec
    }
package com.ae_health.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import com.ae_health.presentation.ui.theme.Dimens.ICON_RADIUS

object Shapes {
    val ICON_ROUNDED = RoundedCornerShape(ICON_RADIUS)
    val BOTTOM_ICON_ROUNDED = RoundedCornerShape(topStart = ICON_RADIUS, topEnd = ICON_RADIUS)
}
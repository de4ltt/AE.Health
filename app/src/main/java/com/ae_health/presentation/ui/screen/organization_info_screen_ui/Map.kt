package com.ae_health.presentation.ui.screen.organization_info_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ae_health.R
import com.ae_health.presentation.theme.Shapes
import com.ae_health.presentation.ui.theme.ExtendedTheme

@Composable
fun Map(
    modifier: Modifier = Modifier,
    lat: Float?,
    lon: Float?
) {

    val uri = "https://static.maps.2gis.com/1.0?s=600x300&c=$lat,$lon&z=16&pt=$lat,$lon"

    AsyncImage(
        modifier = modifier
            .clip(Shapes.ICON_ROUNDED)
            .fillMaxWidth()
            .aspectRatio(2f / 1f)
            .background(ExtendedTheme.extendedColors.secondaryContainer),
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.location),
        contentDescription = ""
    )
}
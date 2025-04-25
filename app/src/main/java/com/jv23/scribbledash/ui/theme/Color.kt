package com.jv23.scribbledash.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Primary = Color(0xFF238CFF)
val OnPrimary = Color(0xFFFFFFFF)
val Secondary = Color(0xFFAB5CFA)
val TertiaryContainer = Color(0xFFFA852C)
val Error = Color(0xFFEF1242)
val Success = Color(0xFF0DD280)
val Background = Color(0xFFFEFAF6)
val OnBackground = Color(0xFF514437)
val OnBackgroundVariant = Color(0xFF7F7163)
val SurfaceHigh = Color(0xFFFFFFFF)
val SurfaceLow = Color(0xFFEEE7E0)
val SurfaceLowest = Color(0xFFE1D5CA)
val OnSurface = Color(0xFFA5978A)
val OnSurfaceVariant = Color(0xFFF6F1EC)
val HourGlassBackground = Color(0xFF742EFC)
val BoltBackground = Color(0xFF01D2FC)
val BrandTertiary = Color(0xFFFA852C)


val BackgroundBrush: Brush
    @Composable
    get() = Brush.linearGradient(
        colors = listOf(Color(0xFFFEFAF6),Color(0xFFFFF1E2))
    )
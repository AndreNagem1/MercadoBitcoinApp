package com.mercado.bitcoin.core_ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val BsLightPalette = AppColors(
    white = Color.White,
    primary = Color(0xFF000000),
)

val LocalBsColors = staticCompositionLocalOf {
    BsLightPalette
}

val LocalBsTypography = staticCompositionLocalOf {
    BsTypographyConst
}

@Composable
fun AppTheme(
    colorPalette: AppColors = BsLightPalette,
    content: @Composable () -> Unit
) {
    MaterialTheme {
        CompositionLocalProvider(
            LocalBsColors provides colorPalette,
            LocalBsTypography provides BsTypographyConst,
            content = content,
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalBsColors.current
    val typography: AppTypography
        @Composable
        get() = LocalBsTypography.current
}
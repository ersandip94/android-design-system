package com.codewithsandip.ds.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.codewithsandip.ds.tokens.CWSShape
import com.codewithsandip.ds.tokens.CWSSpacing
import com.codewithsandip.ds.tokens.CWSTypography

/**
 * Root theme for the CodeWithSandip Design System. Provides the CWS CompositionLocals and also
 * mirrors them into [MaterialTheme] so Material 3 components inside CWS components stay on-brand.
 *
 * @param colorScheme Semantic colors; defaults to dark/light based on the system setting.
 * @param typography Type scale. @param shapes Corner scale. @param spacing Spacing scale.
 * @param content Themed content.
 */
@Composable
public fun CWSTheme(
    colorScheme: CWSColorScheme = if (isSystemInDarkTheme()) cwsDarkColorScheme() else cwsLightColorScheme(),
    typography: CWSTypography = CWSTypography,
    shapes: CWSShape = CWSShape,
    spacing: CWSSpacing = CWSSpacing,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCWSColorScheme provides colorScheme,
        LocalCWSTypography provides typography,
        LocalCWSShapes provides shapes,
        LocalCWSSpacing provides spacing,
    ) {
        MaterialTheme(
            colorScheme = colorScheme.toMaterialColorScheme(),
            typography = typography.toMaterialTypography(),
            shapes = shapes.toMaterialShapes(),
            content = content,
        )
    }
}

private fun CWSColorScheme.toMaterialColorScheme(): ColorScheme =
    lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        surface = surface,
        onSurface = onSurface,
        background = background,
        onBackground = onBackground,
        error = error,
        onError = onError,
        outline = outline,
        outlineVariant = outlineVariant,
        scrim = scrim,
    )

private fun CWSTypography.toMaterialTypography(): Typography =
    Typography(
        displayLarge = DisplayLarge,
        displayMedium = DisplayMedium,
        displaySmall = DisplaySmall,
        headlineLarge = HeadlineLarge,
        headlineMedium = HeadlineMedium,
        headlineSmall = HeadlineSmall,
        titleLarge = TitleLarge,
        titleMedium = TitleMedium,
        titleSmall = TitleSmall,
        bodyLarge = BodyLarge,
        bodyMedium = BodyMedium,
        bodySmall = BodySmall,
        labelLarge = LabelLarge,
        labelMedium = LabelMedium,
        labelSmall = LabelSmall,
    )

private fun CWSShape.toMaterialShapes(): Shapes =
    Shapes(
        extraSmall = ExtraSmall,
        small = Small,
        medium = Medium,
        large = Large,
        extraLarge = ExtraLarge,
    )

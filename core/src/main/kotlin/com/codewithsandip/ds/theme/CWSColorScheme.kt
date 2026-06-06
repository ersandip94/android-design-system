package com.codewithsandip.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Semantic color roles for the design system. Components read these (via the theme) instead of
 * raw palette tokens, so a single scheme swap re-themes everything. See
 * [cwsLightColorScheme] / [cwsDarkColorScheme] for the defaults.
 */
@Immutable
public data class CWSColorScheme(
    public val primary: Color,
    public val onPrimary: Color,
    public val primaryContainer: Color,
    public val onPrimaryContainer: Color,
    public val secondary: Color,
    public val onSecondary: Color,
    public val surface: Color,
    public val onSurface: Color,
    public val background: Color,
    public val onBackground: Color,
    public val error: Color,
    public val onError: Color,
    public val outline: Color,
    public val outlineVariant: Color,
    public val scrim: Color,
)

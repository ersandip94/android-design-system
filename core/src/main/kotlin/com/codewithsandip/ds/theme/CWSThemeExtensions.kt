package com.codewithsandip.ds.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import com.codewithsandip.ds.tokens.CWSShape
import com.codewithsandip.ds.tokens.CWSSpacing
import com.codewithsandip.ds.tokens.CWSTypography

/** Snapshot of all CWS theme values, exposed via [MaterialTheme.cws]. */
@Immutable
public data class CWSThemeData(
    public val colorScheme: CWSColorScheme,
    public val typography: CWSTypography,
    public val shapes: CWSShape,
    public val spacing: CWSSpacing,
)

/**
 * Convenience accessor for CWS theme values from anywhere Material is in scope, e.g.
 * `MaterialTheme.cws.colorScheme.primary` or `MaterialTheme.cws.spacing.md`.
 */
public val MaterialTheme.cws: CWSThemeData
    @Composable
    @ReadOnlyComposable
    get() = CWSThemeData(
        colorScheme = LocalCWSColorScheme.current,
        typography = LocalCWSTypography.current,
        shapes = LocalCWSShapes.current,
        spacing = LocalCWSSpacing.current,
    )

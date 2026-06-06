package com.codewithsandip.ds.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.codewithsandip.ds.tokens.CWSShape
import com.codewithsandip.ds.tokens.CWSSpacing
import com.codewithsandip.ds.tokens.CWSTypography

/** Current [CWSColorScheme]; defaults to the light scheme outside a [CWSTheme]. */
public val LocalCWSColorScheme: ProvidableCompositionLocal<CWSColorScheme> =
    staticCompositionLocalOf { cwsLightColorScheme() }

/** Current type scale. */
public val LocalCWSTypography: ProvidableCompositionLocal<CWSTypography> =
    staticCompositionLocalOf { CWSTypography }

/** Current corner-radius scale. */
public val LocalCWSShapes: ProvidableCompositionLocal<CWSShape> =
    staticCompositionLocalOf { CWSShape }

/** Current spacing scale. */
public val LocalCWSSpacing: ProvidableCompositionLocal<CWSSpacing> =
    staticCompositionLocalOf { CWSSpacing }

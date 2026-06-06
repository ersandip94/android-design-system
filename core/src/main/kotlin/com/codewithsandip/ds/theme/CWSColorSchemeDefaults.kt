package com.codewithsandip.ds.theme

import androidx.compose.ui.graphics.Color
import com.codewithsandip.ds.tokens.CWSColors

/**
 * Light color scheme. [primaryBrand] defaults to [CWSColors.Brand900]; pass a custom brand color
 * to re-skin the design system without touching components.
 */
public fun cwsLightColorScheme(primaryBrand: Color = CWSColors.Brand900): CWSColorScheme =
    CWSColorScheme(
        primary = primaryBrand,
        onPrimary = CWSColors.White,
        primaryContainer = CWSColors.Brand100,
        onPrimaryContainer = CWSColors.Brand900,
        secondary = CWSColors.Brand500,
        onSecondary = CWSColors.White,
        surface = CWSColors.White,
        onSurface = CWSColors.Neutral900,
        background = CWSColors.Neutral50,
        onBackground = CWSColors.Neutral900,
        error = CWSColors.Error500,
        onError = CWSColors.White,
        outline = CWSColors.Neutral300,
        outlineVariant = CWSColors.Neutral200,
        scrim = CWSColors.Black,
    )

/**
 * Dark color scheme. [primaryBrand] defaults to [CWSColors.Brand300] — a lighter brand shade that
 * keeps contrast on dark surfaces.
 */
public fun cwsDarkColorScheme(primaryBrand: Color = CWSColors.Brand300): CWSColorScheme =
    CWSColorScheme(
        primary = primaryBrand,
        onPrimary = CWSColors.Brand900,
        primaryContainer = CWSColors.Brand700,
        onPrimaryContainer = CWSColors.Brand100,
        secondary = CWSColors.Brand300,
        onSecondary = CWSColors.Brand900,
        surface = CWSColors.Neutral800,
        onSurface = CWSColors.Neutral100,
        background = CWSColors.Neutral900,
        onBackground = CWSColors.Neutral100,
        error = CWSColors.Error300,
        onError = CWSColors.Neutral900,
        outline = CWSColors.Neutral600,
        outlineVariant = CWSColors.Neutral700,
        scrim = CWSColors.Black,
    )

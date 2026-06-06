package com.codewithsandip.ds.tokens

import androidx.compose.ui.graphics.Color

/**
 * Raw color palette for the CWS Design System, derived from the brand color
 * `#0C3A25`. These are primitive tokens — semantic mapping happens in the theme layer
 * ([com.codewithsandip.ds.theme]). Prefer theme colors in components, not these directly.
 */
public object CWSColors {
    // Brand — derived from #0C3A25
    public val Brand900: Color = Color(0xFF0C3A25)
    public val Brand700: Color = Color(0xFF155E3C)
    public val Brand500: Color = Color(0xFF1E8A57)
    public val Brand300: Color = Color(0xFF5CB88A)
    public val Brand100: Color = Color(0xFFD4EFE2)
    public val Brand50: Color = Color(0xFFEDF8F3)

    // Neutral — text, borders, surfaces
    public val Neutral900: Color = Color(0xFF111827)
    public val Neutral800: Color = Color(0xFF1F2937)
    public val Neutral700: Color = Color(0xFF374151)
    public val Neutral600: Color = Color(0xFF4B5563)
    public val Neutral500: Color = Color(0xFF6B7280)
    public val Neutral400: Color = Color(0xFF9CA3AF)
    public val Neutral300: Color = Color(0xFFD1D5DB)
    public val Neutral200: Color = Color(0xFFE5E7EB)
    public val Neutral100: Color = Color(0xFFF3F4F6)
    public val Neutral50: Color = Color(0xFFF9FAFB)

    // Error
    public val Error100: Color = Color(0xFFFEE2E2)
    public val Error300: Color = Color(0xFFFCA5A5)
    public val Error500: Color = Color(0xFFDC2626)
    public val Error700: Color = Color(0xFFB91C1C)

    // Warning
    public val Warning100: Color = Color(0xFFFEF3C7)
    public val Warning300: Color = Color(0xFFFCD34D)
    public val Warning500: Color = Color(0xFFD97706)
    public val Warning700: Color = Color(0xFFB45309)

    // Success
    public val Success100: Color = Color(0xFFDCFCE7)
    public val Success300: Color = Color(0xFF86EFAC)
    public val Success500: Color = Color(0xFF16A34A)
    public val Success700: Color = Color(0xFF15803D)

    // Absolutes
    public val White: Color = Color(0xFFFFFFFF)
    public val Black: Color = Color(0xFF000000)
}

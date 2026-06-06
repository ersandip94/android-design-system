package com.codewithsandip.ds.component.button

import androidx.compose.ui.graphics.Color
import com.codewithsandip.ds.theme.cwsLightColorScheme
import org.junit.Assert.assertEquals
import org.junit.Test

class CWSButtonTest {

    private val scheme = cwsLightColorScheme()

    @Test
    fun primaryVariant_usesPrimaryContainerAndOnPrimaryContent() {
        val colors = resolveButtonColors(scheme, CWSButtonVariant.Primary)
        assertEquals(scheme.primary, colors.container)
        assertEquals(scheme.onPrimary, colors.content)
        assertEquals(Color.Transparent, colors.border)
    }

    @Test
    fun secondaryVariant_usesOutlineBorderAndPrimaryContent() {
        val colors = resolveButtonColors(scheme, CWSButtonVariant.Secondary)
        assertEquals(Color.Transparent, colors.container)
        assertEquals(scheme.primary, colors.content)
        assertEquals(scheme.outline, colors.border)
    }

    @Test
    fun ghostVariant_hasTransparentBackground() {
        val colors = resolveButtonColors(scheme, CWSButtonVariant.Ghost)
        assertEquals(Color.Transparent, colors.container)
        assertEquals(Color.Transparent, colors.border)
        assertEquals(scheme.primary, colors.content)
    }

    @Test
    fun dangerVariant_usesErrorColor() {
        val colors = resolveButtonColors(scheme, CWSButtonVariant.Danger)
        assertEquals(scheme.error, colors.container)
        assertEquals(scheme.onError, colors.content)
    }

    @Test
    fun disabledAlpha_is38Percent() {
        assertEquals(0.38f, CWSButtonDisabledAlpha, 0.0f)
    }
}

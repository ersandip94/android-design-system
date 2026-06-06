package com.codewithsandip.ds.tokens

import org.junit.Assert.assertTrue
import org.junit.Test

class CWSTypographyTest {

    @Test
    fun displayLarge_isLargerThanHeadlineLarge() {
        assertTrue(
            CWSTypography.DisplayLarge.fontSize.value > CWSTypography.HeadlineLarge.fontSize.value,
        )
    }

    @Test
    fun bodyMedium_lineHeightExceedsFontSize() {
        assertTrue(
            CWSTypography.BodyMedium.lineHeight.value > CWSTypography.BodyMedium.fontSize.value,
        )
    }

    @Test
    fun typeScale_descendsFromDisplayToLabel() {
        val sizes = listOf(
            CWSTypography.DisplayLarge.fontSize.value,
            CWSTypography.HeadlineLarge.fontSize.value,
            CWSTypography.TitleLarge.fontSize.value,
            CWSTypography.BodyLarge.fontSize.value,
            CWSTypography.LabelSmall.fontSize.value,
        )
        assertTrue(sizes.zipWithNext().all { (a, b) -> a > b })
    }
}

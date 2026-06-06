package com.codewithsandip.ds.component.badge

import com.codewithsandip.ds.tokens.CWSColors
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CWSBadgeTest {

    @Test
    fun formatCount_belowMax_showsExactNumber() {
        assertEquals("3", formatCount(count = 3, max = 99))
        assertEquals("99", formatCount(count = 99, max = 99))
    }

    @Test
    fun formatCount_aboveMax_showsPlus() {
        assertEquals("99+", formatCount(count = 100, max = 99))
        assertEquals("9+", formatCount(count = 250, max = 9))
    }

    @Test
    fun errorStatus_usesErrorColor() {
        val (container, content) = resolveBadgeColors(CWSBadgeStatus.Error)
        assertEquals(CWSColors.Error500, container)
        assertEquals(CWSColors.White, content)
    }

    @Test
    fun successStatus_usesSuccessColor() {
        assertEquals(CWSColors.Success500, resolveBadgeColors(CWSBadgeStatus.Success).first)
    }

    @Test
    fun eachStatus_hasDistinctContainerColor() {
        val containers = CWSBadgeStatus.entries.map { resolveBadgeColors(it).first }
        assertEquals(containers.size, containers.toSet().size)
    }

    @Test
    fun infoAndNeutral_differ() {
        assertNotEquals(
            resolveBadgeColors(CWSBadgeStatus.Info).first,
            resolveBadgeColors(CWSBadgeStatus.Neutral).first,
        )
    }
}

package com.codewithsandip.ds.tokens

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CWSSpacingTest {

    private val ordered = listOf(
        CWSSpacing.none, CWSSpacing.xxs, CWSSpacing.xs, CWSSpacing.sm,
        CWSSpacing.md, CWSSpacing.lg, CWSSpacing.xl, CWSSpacing.xxl, CWSSpacing.xxxl,
    )

    @Test
    fun allValues_areNonNegative() {
        assertTrue(ordered.all { it >= 0.dp })
    }

    @Test
    fun values_areStrictlyAscending() {
        val sorted = ordered.sorted()
        assertEquals(ordered, sorted)
        assertEquals(ordered.size, ordered.toSet().size)
    }
}

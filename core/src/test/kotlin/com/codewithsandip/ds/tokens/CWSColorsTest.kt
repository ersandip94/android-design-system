package com.codewithsandip.ds.tokens

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CWSColorsTest {

    @Test
    fun brand900_matchesBrandHex() {
        assertEquals(Color(0xFF0C3A25), CWSColors.Brand900)
    }

    @Test
    fun brand50_matchesHex() {
        assertEquals(Color(0xFFEDF8F3), CWSColors.Brand50)
    }

    @Test
    fun brandShades_areDistinct() {
        val brand = listOf(
            CWSColors.Brand50, CWSColors.Brand100, CWSColors.Brand300,
            CWSColors.Brand500, CWSColors.Brand700, CWSColors.Brand900,
        )
        assertEquals(brand.size, brand.toSet().size)
    }

    @Test
    fun neutralShades_areDistinct() {
        val neutral = listOf(
            CWSColors.Neutral50, CWSColors.Neutral100, CWSColors.Neutral200,
            CWSColors.Neutral300, CWSColors.Neutral400, CWSColors.Neutral500,
            CWSColors.Neutral600, CWSColors.Neutral700, CWSColors.Neutral800,
            CWSColors.Neutral900,
        )
        assertEquals(neutral.size, neutral.toSet().size)
    }

    @Test
    fun statusColors_areDistinctFromBrand() {
        val brand = setOf(
            CWSColors.Brand50, CWSColors.Brand100, CWSColors.Brand300,
            CWSColors.Brand500, CWSColors.Brand700, CWSColors.Brand900,
        )
        val status = listOf(
            CWSColors.Error100, CWSColors.Error300, CWSColors.Error500, CWSColors.Error700,
            CWSColors.Warning100, CWSColors.Warning300, CWSColors.Warning500, CWSColors.Warning700,
            CWSColors.Success100, CWSColors.Success300, CWSColors.Success500, CWSColors.Success700,
        )
        assertTrue(status.none { it in brand })
        // status colors are also distinct among themselves
        assertEquals(status.size, status.toSet().size)
    }
}

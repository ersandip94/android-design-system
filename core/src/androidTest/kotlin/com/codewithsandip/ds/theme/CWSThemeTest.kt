package com.codewithsandip.ds.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.v2.createComposeRule
import com.codewithsandip.ds.tokens.CWSColors
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CWSThemeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun lightColorSchemeApplied() {
        var primary: Color? = null
        composeRule.setContent {
            CWSTheme(colorScheme = cwsLightColorScheme()) {
                primary = LocalCWSColorScheme.current.primary
            }
        }
        composeRule.runOnIdle { assertEquals(CWSColors.Brand900, primary) }
    }

    @Test
    fun darkColorSchemeApplied() {
        var primary: Color? = null
        composeRule.setContent {
            CWSTheme(colorScheme = cwsDarkColorScheme()) {
                primary = LocalCWSColorScheme.current.primary
            }
        }
        composeRule.runOnIdle { assertEquals(CWSColors.Brand300, primary) }
    }

    @Test
    fun customBrandColorApplied() {
        val custom = Color(0xFF123456)
        var primary: Color? = null
        composeRule.setContent {
            CWSTheme(colorScheme = cwsLightColorScheme(primaryBrand = custom)) {
                primary = LocalCWSColorScheme.current.primary
            }
        }
        composeRule.runOnIdle { assertEquals(custom, primary) }
    }

    @Test
    fun materialThemeInterop() {
        var material: Color? = null
        var cws: Color? = null
        composeRule.setContent {
            CWSTheme(colorScheme = cwsLightColorScheme()) {
                material = MaterialTheme.colorScheme.primary
                cws = LocalCWSColorScheme.current.primary
            }
        }
        composeRule.runOnIdle { assertEquals(cws, material) }
    }
}

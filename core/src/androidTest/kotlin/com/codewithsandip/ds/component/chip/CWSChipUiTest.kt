package com.codewithsandip.ds.component.chip

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CWSChipUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun filterChip_displaysLabelAndClicks() {
        var clicked = false
        composeRule.setContent {
            CWSTheme { CWSChip("All", onClick = { clicked = true }, variant = CWSChipVariant.Filter) }
        }
        composeRule.onNodeWithText("All").assertIsDisplayed().performClick()
        composeRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun inputChip_closeActionInvoked() {
        var closed = false
        composeRule.setContent {
            CWSTheme { CWSChip("team", onClick = {}, variant = CWSChipVariant.Input, onClose = { closed = true }) }
        }
        composeRule.onNodeWithContentDescription("Remove team").performClick()
        composeRule.runOnIdle { assertTrue(closed) }
    }

    @Test
    fun suggestionChip_clicks() {
        var clicked = false
        composeRule.setContent {
            CWSTheme { CWSChip("Kotlin", onClick = { clicked = true }, variant = CWSChipVariant.Suggestion) }
        }
        composeRule.onNodeWithText("Kotlin").performClick()
        composeRule.runOnIdle { assertTrue(clicked) }
    }
}

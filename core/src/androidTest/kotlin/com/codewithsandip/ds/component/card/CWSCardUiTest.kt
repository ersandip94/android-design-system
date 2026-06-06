package com.codewithsandip.ds.component.card

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CWSCardUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displaysContent() {
        composeRule.setContent {
            CWSTheme { CWSCard { Text("Card body") } }
        }
        composeRule.onNodeWithText("Card body").assertIsDisplayed()
    }

    @Test
    fun clickableCard_invokesOnClick() {
        var clicked = false
        composeRule.setContent {
            CWSTheme { CWSCard(onClick = { clicked = true }) { Text("Tap me") } }
        }
        composeRule.onNode(hasClickAction()).assertHasClickAction().performClick()
        composeRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun allVariants_render() {
        composeRule.setContent {
            CWSTheme {
                CWSCardVariant.entries.forEach { variant ->
                    CWSCard(variant = variant) { Text(variant.name) }
                }
            }
        }
        CWSCardVariant.entries.forEach { variant ->
            composeRule.onNodeWithText(variant.name).assertIsDisplayed()
        }
    }
}

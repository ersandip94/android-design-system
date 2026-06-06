package com.codewithsandip.ds.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class CWSButtonUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displaysText() {
        composeRule.setContent { CWSTheme { CWSButton(text = "Click me", onClick = {}) } }
        composeRule.onNodeWithText("Click me").assertIsDisplayed()
    }

    @Test
    fun isClickable() {
        composeRule.setContent { CWSTheme { CWSButton(text = "Click me", onClick = {}) } }
        composeRule.onNode(hasClickAction()).assertIsDisplayed()
    }

    @Test
    fun disabledButton_isNotEnabledAndDoesNotClick() {
        var clicked = false
        composeRule.setContent {
            CWSTheme { CWSButton(text = "Click me", onClick = { clicked = true }, enabled = false) }
        }
        composeRule.onNode(hasClickAction()).assertIsNotEnabled()
        composeRule.onNodeWithText("Click me").performClick()
        composeRule.runOnIdle { assertFalse(clicked) }
    }

    @Test
    fun loadingButton_showsIndicatorAndHidesText() {
        composeRule.setContent {
            CWSTheme { CWSButton(text = "Click me", onClick = {}, loading = true) }
        }
        composeRule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertIsDisplayed()
        composeRule.onNodeWithText("Click me").assertDoesNotExist()
    }

    @Test
    fun leadingIcon_isDisplayed() {
        composeRule.setContent {
            CWSTheme { CWSButton(text = "Add", onClick = {}, leadingIcon = Icons.Default.Add) }
        }
        composeRule.onNodeWithText("Add").assertIsDisplayed()
    }

    @Test
    fun meetsMinimumTouchTarget() {
        composeRule.setContent { CWSTheme { CWSButton(text = "Hi", onClick = {}) } }
        composeRule.onNode(hasClickAction())
            .assertHeightIsAtLeast(48.dp)
            .assertWidthIsAtLeast(48.dp)
    }

    @Test
    fun contentDescription_isApplied() {
        composeRule.setContent {
            CWSTheme {
                CWSButton(text = "OK", onClick = {}, contentDescription = "Submit form")
            }
        }
        composeRule.onNode(hasClickAction()).assertContentDescriptionEquals("Submit form")
    }

    @Test
    fun allVariants_render() {
        composeRule.setContent {
            CWSTheme {
                Column {
                    CWSButtonVariant.entries.forEach { variant ->
                        CWSButton(text = variant.name, onClick = {}, variant = variant)
                    }
                }
            }
        }
        CWSButtonVariant.entries.forEach { variant ->
            composeRule.onNodeWithText(variant.name).assertIsDisplayed()
        }
    }
}

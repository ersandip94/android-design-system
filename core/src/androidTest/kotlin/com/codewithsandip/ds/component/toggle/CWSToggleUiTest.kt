package com.codewithsandip.ds.component.toggle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CWSToggleUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun checkbox_togglesOnClick() {
        composeRule.setContent {
            var checked by remember { mutableStateOf(false) }
            CWSTheme { CWSCheckbox(checked = checked, onCheckedChange = { checked = it }, label = "Accept") }
        }
        composeRule.onNode(isToggleable()).assertIsOff()
        composeRule.onNodeWithText("Accept").performClick()
        composeRule.onNode(isToggleable()).assertIsOn()
    }

    @Test
    fun radioButton_selectsOnClick() {
        var selected = false
        composeRule.setContent {
            CWSTheme { CWSRadioButton(selected = selected, onClick = { selected = true }, label = "Option A") }
        }
        composeRule.onNodeWithText("Option A").performClick()
        composeRule.runOnIdle { assertTrue(selected) }
    }

    @Test
    fun switch_togglesOnClick() {
        composeRule.setContent {
            var checked by remember { mutableStateOf(false) }
            CWSTheme { CWSSwitch(checked = checked, onCheckedChange = { checked = it }, label = "Wi-Fi") }
        }
        composeRule.onNode(isToggleable()).assertIsOff()
        composeRule.onNodeWithText("Wi-Fi").performClick()
        composeRule.onNode(isToggleable()).assertIsOn()
    }

    @Test
    fun selectedRadioButton_isSelected() {
        composeRule.setContent {
            CWSTheme { CWSRadioButton(selected = true, onClick = {}, label = "Chosen") }
        }
        composeRule.onNodeWithText("Chosen").assertIsSelected()
    }

    @Test
    fun slider_isDisplayed() {
        composeRule.setContent { CWSTheme { CWSSlider(value = 0.5f, onValueChange = {}) } }
        composeRule.onNode(SemanticsMatcher.keyIsDefined(SemanticsActions.SetProgress)).assertIsDisplayed()
    }
}

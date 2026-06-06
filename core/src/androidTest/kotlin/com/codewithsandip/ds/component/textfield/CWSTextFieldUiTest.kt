package com.codewithsandip.ds.component.textfield

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Rule
import org.junit.Test

class CWSTextFieldUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displaysLabel() {
        composeRule.setContent { CWSTheme { CWSTextField("", onValueChange = {}, label = "Email") } }
        composeRule.onNodeWithText("Email").assertIsDisplayed()
    }

    @Test
    fun acceptsInput() {
        composeRule.setContent {
            var text by remember { mutableStateOf("") }
            CWSTheme { CWSTextField(text, onValueChange = { text = it }, label = "Email") }
        }
        composeRule.onNode(hasSetTextAction()).performTextInput("hello")
        composeRule.onNodeWithText("hello").assertIsDisplayed()
    }

    @Test
    fun errorTextShown() {
        composeRule.setContent {
            CWSTheme { CWSTextField("x", onValueChange = {}, label = "Email", isError = true, errorText = "Required") }
        }
        composeRule.onNodeWithText("Required").assertIsDisplayed()
    }

    @Test
    fun helperTextShown() {
        composeRule.setContent {
            CWSTheme { CWSTextField("x", onValueChange = {}, label = "Email", helperText = "Enter your email") }
        }
        composeRule.onNodeWithText("Enter your email").assertIsDisplayed()
    }

    @Test
    fun disabledFieldNotEditable() {
        composeRule.setContent {
            CWSTheme { CWSTextField("x", onValueChange = {}, label = "Email", enabled = false) }
        }
        composeRule.onNode(hasSetTextAction()).assertIsNotEnabled()
    }

    @Test
    fun placeholderShown() {
        composeRule.setContent {
            CWSTheme { CWSTextField("", onValueChange = {}, placeholder = "Type here...") }
        }
        composeRule.onNodeWithText("Type here...").assertIsDisplayed()
    }

    @Test
    fun placeholderClearedOnInput() {
        composeRule.setContent {
            var text by remember { mutableStateOf("") }
            CWSTheme { CWSTextField(text, onValueChange = { text = it }, placeholder = "Type here...") }
        }
        composeRule.onNode(hasSetTextAction()).performTextInput("hi")
        composeRule.onNodeWithText("Type here...").assertDoesNotExist()
    }
}

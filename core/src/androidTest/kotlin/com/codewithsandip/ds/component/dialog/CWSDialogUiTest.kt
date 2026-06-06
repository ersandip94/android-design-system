package com.codewithsandip.ds.component.dialog

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CWSDialogUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun showsTitleAndText() {
        composeRule.setContent {
            CWSTheme {
                CWSDialog(onDismissRequest = {}, title = "Save changes?", text = "Body copy", confirmLabel = "Save", onConfirm = {})
            }
        }
        composeRule.onNodeWithText("Save changes?").assertIsDisplayed()
        composeRule.onNodeWithText("Body copy").assertIsDisplayed()
    }

    @Test
    fun confirmButton_invokesOnConfirm() {
        var confirmed = false
        composeRule.setContent {
            CWSTheme {
                CWSDialog(onDismissRequest = {}, title = "Title", confirmLabel = "Save", onConfirm = { confirmed = true })
            }
        }
        composeRule.onNodeWithText("Save").performClick()
        composeRule.runOnIdle { assertTrue(confirmed) }
    }

    @Test
    fun dismissButton_invokesOnDismiss() {
        var dismissed = false
        composeRule.setContent {
            CWSTheme {
                CWSDialog(onDismissRequest = {}, title = "Title", confirmLabel = "Save", onConfirm = {}, dismissLabel = "Cancel", onDismiss = { dismissed = true })
            }
        }
        composeRule.onNodeWithText("Cancel").performClick()
        composeRule.runOnIdle { assertTrue(dismissed) }
    }
}

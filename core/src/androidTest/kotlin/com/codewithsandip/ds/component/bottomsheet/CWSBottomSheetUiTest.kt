package com.codewithsandip.ds.component.bottomsheet

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Rule
import org.junit.Test

class CWSBottomSheetUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun showsContent() {
        composeRule.setContent {
            CWSTheme {
                CWSBottomSheet(onDismissRequest = {}) {
                    Text("Sheet content")
                }
            }
        }
        composeRule.onNodeWithText("Sheet content").assertIsDisplayed()
    }
}

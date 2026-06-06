package com.codewithsandip.ds.component.badge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Rule
import org.junit.Test

class CWSBadgeUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun countBadge_displaysCount() {
        composeRule.setContent { CWSTheme { CWSCountBadge(count = 3) } }
        composeRule.onNodeWithText("3").assertIsDisplayed()
    }

    @Test
    fun countBadge_overMax_showsPlus() {
        composeRule.setContent { CWSTheme { CWSCountBadge(count = 100, max = 99) } }
        composeRule.onNodeWithText("99+").assertIsDisplayed()
    }

    @Test
    fun statusLabel_displayed() {
        composeRule.setContent { CWSTheme { CWSBadge(text = "New", status = CWSBadgeStatus.Info) } }
        composeRule.onNodeWithText("New").assertIsDisplayed()
    }

    @Test
    fun dotBadge_hasContentDescription() {
        composeRule.setContent { CWSTheme { CWSBadge(status = CWSBadgeStatus.Error) } }
        composeRule.onNodeWithContentDescription("New").assertIsDisplayed()
    }
}

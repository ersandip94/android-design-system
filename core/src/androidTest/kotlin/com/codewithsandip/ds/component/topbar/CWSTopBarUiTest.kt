package com.codewithsandip.ds.component.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CWSTopBarUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displaysTitle() {
        composeRule.setContent { CWSTheme { CWSTopBar(title = "Components") } }
        composeRule.onNodeWithText("Components").assertIsDisplayed()
    }

    @Test
    fun navigationIcon_invokesClick() {
        var navClicked = false
        composeRule.setContent {
            CWSTheme {
                CWSTopBar(
                    title = "Components",
                    navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onNavigationClick = { navClicked = true },
                    navigationContentDescription = "Back",
                )
            }
        }
        composeRule.onNodeWithContentDescription("Back").performClick()
        composeRule.runOnIdle { assertTrue(navClicked) }
    }

    @Test
    fun action_invokesClick() {
        var actionClicked = false
        composeRule.setContent {
            CWSTheme {
                CWSTopBar(title = "Components", actions = {
                    IconButton(onClick = { actionClicked = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                })
            }
        }
        composeRule.onNodeWithContentDescription("More").performClick()
        composeRule.runOnIdle { assertTrue(actionClicked) }
    }
}

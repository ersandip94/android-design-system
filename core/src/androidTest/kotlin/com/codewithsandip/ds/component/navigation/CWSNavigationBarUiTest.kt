package com.codewithsandip.ds.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.codewithsandip.ds.theme.CWSTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CWSNavigationBarUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val items = listOf(
        CWSNavigationItem("Home", Icons.Default.Home),
        CWSNavigationItem("Favorites", Icons.Default.Favorite, badgeCount = 5),
        CWSNavigationItem("Settings", Icons.Default.Settings),
    )

    @Test
    fun displaysAllItems() {
        composeRule.setContent {
            CWSTheme { CWSNavigationBar(items = items, selectedIndex = 0, onSelect = {}) }
        }
        items.forEach { composeRule.onNodeWithText(it.label).assertIsDisplayed() }
    }

    @Test
    fun selectingTab_invokesOnSelectWithIndex() {
        var selected = -1
        composeRule.setContent {
            CWSTheme { CWSNavigationBar(items = items, selectedIndex = 0, onSelect = { selected = it }) }
        }
        composeRule.onNodeWithText("Settings").performClick()
        composeRule.runOnIdle { assertEquals(2, selected) }
    }

    @Test
    fun badgeCount_displayed() {
        composeRule.setContent {
            CWSTheme { CWSNavigationBar(items = items, selectedIndex = 0, onSelect = {}) }
        }
        composeRule.onNodeWithText("5").assertIsDisplayed()
    }
}

package com.codewithsandip.ds.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.tools.screenshot.PreviewTest
import com.codewithsandip.ds.foundation.LightDarkPreview
import com.codewithsandip.ds.theme.CWSTheme

/**
 * Golden-image previews rendered by the `com.android.compose.screenshot` plugin. `@PreviewTest`
 * marks each function for discovery; `@LightDarkPreview` produces a light and a dark golden.
 *
 * - Record/update: `./gradlew :core:updateDebugScreenshotTest`
 * - Verify (CI):   `./gradlew :core:validateDebugScreenshotTest`
 */
class CWSButtonScreenshotTest {

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonPrimaryMedium() {
        CWSTheme { CWSButton("Primary", onClick = {}, variant = CWSButtonVariant.Primary) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonSecondaryMedium() {
        CWSTheme { CWSButton("Secondary", onClick = {}, variant = CWSButtonVariant.Secondary) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonGhostMedium() {
        CWSTheme { CWSButton("Ghost", onClick = {}, variant = CWSButtonVariant.Ghost) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonDangerMedium() {
        CWSTheme { CWSButton("Delete", onClick = {}, variant = CWSButtonVariant.Danger) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonSizes() {
        CWSTheme {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                CWSButton("Small", onClick = {}, size = CWSButtonSize.Small)
                CWSButton("Medium", onClick = {}, size = CWSButtonSize.Medium)
                CWSButton("Large", onClick = {}, size = CWSButtonSize.Large)
            }
        }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonLoading() {
        CWSTheme { CWSButton("Submit", onClick = {}, loading = true) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonDisabled() {
        CWSTheme { CWSButton("Disabled", onClick = {}, enabled = false) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonWithLeadingIcon() {
        CWSTheme { CWSButton("Add item", onClick = {}, leadingIcon = Icons.Default.Add) }
    }

    @PreviewTest
    @LightDarkPreview
    @Composable
    fun buttonWithTrailingIcon() {
        CWSTheme { CWSButton("Next", onClick = {}, trailingIcon = Icons.AutoMirrored.Filled.ArrowForward) }
    }
}

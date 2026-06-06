package com.codewithsandip.ds.foundation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/**
 * Multipreview that renders a composable in both light and dark themes from a single `@Preview`
 * function. Shared across component preview files and the `screenshotTest` source set so one
 * annotation drives the IDE split-pane preview *and* the recorded golden images.
 */
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
internal annotation class LightDarkPreview

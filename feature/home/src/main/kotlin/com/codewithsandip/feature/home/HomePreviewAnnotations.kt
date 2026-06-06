package com.codewithsandip.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/** Light + dark multipreview for the home feature (the design system's own is internal to :core). */
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
internal annotation class HomeLightDarkPreview

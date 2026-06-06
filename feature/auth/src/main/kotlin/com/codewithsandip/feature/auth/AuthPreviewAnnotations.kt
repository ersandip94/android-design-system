package com.codewithsandip.feature.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/** Light + dark multipreview for the auth feature (the design system's own is internal to :core). */
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
internal annotation class AuthLightDarkPreview

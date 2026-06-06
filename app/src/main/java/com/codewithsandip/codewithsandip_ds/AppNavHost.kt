package com.codewithsandip.codewithsandip_ds

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codewithsandip.codewithsandip_ds.theme.ThemeMode
import com.codewithsandip.codewithsandip_ds.ui.ThemeSwitcher
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonSize
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.component.topbar.CWSTopBar
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.ds.theme.cwsDarkColorScheme
import com.codewithsandip.ds.theme.cwsLightColorScheme
import com.codewithsandip.feature.auth.navigation.AuthRoutes
import com.codewithsandip.feature.auth.navigation.authNavGraph
import com.codewithsandip.feature.home.navigation.HomeRoutes
import com.codewithsandip.feature.home.navigation.homeNavGraph

/**
 * Root of the app: a Navigation Compose host wiring the auth graph → home graph, wrapped in
 * [CWSTheme] with the user-selected [ThemeMode] and a [CWSTopBar] carrying the theme switcher
 * (and a sign-out action once authenticated).
 */
@Composable
fun AppNavHost() {
    var themeMode by rememberSaveable { mutableStateOf(ThemeMode.System) }
    val colorScheme = when (themeMode) {
        ThemeMode.Light -> cwsLightColorScheme()
        ThemeMode.Dark -> cwsDarkColorScheme()
        ThemeMode.System -> if (isSystemInDarkTheme()) cwsDarkColorScheme() else cwsLightColorScheme()
    }

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val inHome = currentRoute?.startsWith("home") == true

    val signOut = {
        navController.navigate(AuthRoutes.GRAPH) {
            popUpTo(HomeRoutes.GRAPH) { inclusive = true }
        }
        Unit
    }

    CWSTheme(colorScheme = colorScheme) {
        Scaffold(
            topBar = {
                CWSTopBar(
                    title = titleFor(currentRoute),
                    actions = {
                        ThemeSwitcher(mode = themeMode, onChange = { themeMode = it })
                        if (inHome) {
                            CWSButton(
                                text = "Sign out",
                                onClick = signOut,
                                variant = CWSButtonVariant.Ghost,
                                size = CWSButtonSize.Small,
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AuthRoutes.GRAPH,
                modifier = Modifier.fillMaxSize().padding(innerPadding),
            ) {
                authNavGraph(
                    navController = navController,
                    onAuthenticated = {
                        navController.navigate(HomeRoutes.GRAPH) {
                            popUpTo(AuthRoutes.GRAPH) { inclusive = true }
                        }
                    },
                )
                homeNavGraph(navController = navController)
            }
        }
    }
}

private fun titleFor(route: String?): String = when {
    route == AuthRoutes.LOGIN -> "Sign in"
    route == AuthRoutes.SIGNUP -> "Create account"
    route == HomeRoutes.LIST -> "Destinations"
    route?.startsWith("home/detail") == true -> "Destination"
    else -> "CodeWithSandip"
}

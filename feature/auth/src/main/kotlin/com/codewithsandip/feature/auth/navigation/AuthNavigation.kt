package com.codewithsandip.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.codewithsandip.feature.auth.login.LoginScreen
import com.codewithsandip.feature.auth.signup.SignupScreen

/** Route constants for the auth flow. */
object AuthRoutes {
    const val GRAPH = "auth"
    const val LOGIN = "auth/login"
    const val SIGNUP = "auth/signup"
}

/**
 * Registers the auth navigation graph (login + signup).
 *
 * @param navController used for in-graph navigation between login and signup.
 * @param onAuthenticated called when the user successfully logs in or signs up.
 */
fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    onAuthenticated: () -> Unit,
) {
    navigation(startDestination = AuthRoutes.LOGIN, route = AuthRoutes.GRAPH) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = onAuthenticated,
                onNavigateToSignup = { navController.navigate(AuthRoutes.SIGNUP) },
            )
        }
        composable(AuthRoutes.SIGNUP) {
            SignupScreen(
                onSignupSuccess = onAuthenticated,
                onNavigateToLogin = { navController.popBackStack() },
            )
        }
    }
}

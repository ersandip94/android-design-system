package com.codewithsandip.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.codewithsandip.feature.home.detail.DestinationDetailScreen
import com.codewithsandip.feature.home.detail.DestinationDetailViewModel
import com.codewithsandip.feature.home.list.DestinationListScreen

/** Route constants for the home flow. */
object HomeRoutes {
    const val GRAPH = "home"
    const val LIST = "home/list"
    const val DETAIL = "home/detail/{${DestinationDetailViewModel.ARG_ID}}"

    fun detail(id: String) = "home/detail/$id"
}

/** Registers the home navigation graph (destinations list + detail). */
fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation(startDestination = HomeRoutes.LIST, route = HomeRoutes.GRAPH) {
        composable(HomeRoutes.LIST) {
            DestinationListScreen(
                onDestinationClick = { id -> navController.navigate(HomeRoutes.detail(id)) },
            )
        }
        composable(
            route = HomeRoutes.DETAIL,
            arguments = listOf(navArgument(DestinationDetailViewModel.ARG_ID) { type = NavType.StringType }),
        ) {
            DestinationDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}

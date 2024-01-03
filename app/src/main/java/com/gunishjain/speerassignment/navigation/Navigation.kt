package com.gunishjain.speerassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gunishjain.speerassignment.ui.search.SearchScreenRoute
import com.gunishjain.speerassignment.ui.userdetail.UserDetailRoute

@Composable
fun SetupNavGraph(
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SearchUser.route
    ) {
        composable(
            route = Screen.SearchUser.route
        ) {
            SearchScreenRoute(
                onUserClick = {
                    navController.navigate(route = "userdetail/$it")
                })
        }

        composable(
            route = Screen.UserDetailScreen.route,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                })
        ) { it ->
            val username = it.arguments?.getString("username").toString()
            UserDetailRoute(username)
        }

    }
}
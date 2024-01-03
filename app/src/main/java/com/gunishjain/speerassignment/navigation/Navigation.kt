package com.gunishjain.speerassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gunishjain.speerassignment.ui.search.SearchScreenRoute
import com.gunishjain.speerassignment.ui.userdetail.UserDetailRoute
import com.gunishjain.speerassignment.ui.userlist.UserListRoute

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

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
            UserDetailRoute(navController, username)
        }


        composable(route = Screen.UserList.route,
            arguments = listOf(
                navArgument("follower") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("following") {
                    type = NavType.StringType
                    defaultValue = ""

                }
            )
        ) { it ->
            val follower = it.arguments?.getString("followerUsername").toString()
            val following = it.arguments?.getString("followingUsername").toString()

            UserListRoute(follower = follower, following = following, onUserClick = {
                navController.navigate(route = "userdetail/$it")
            })

        }


    }
}
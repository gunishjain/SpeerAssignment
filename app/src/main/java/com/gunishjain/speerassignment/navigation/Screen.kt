package com.gunishjain.speerassignment.navigation

sealed class Screen(val route: String) {

    object SearchUser : Screen(route = "searchuser")

    object UserDetailScreen: Screen(route = "userdetail/{username}")

}
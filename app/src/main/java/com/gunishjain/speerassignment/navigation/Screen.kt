package com.gunishjain.speerassignment.navigation

sealed class Screen(val route: String) {

    object SearchUser : Screen(route = "searchuser")

    object UserDetailScreen : Screen(route = "userdetail/{username}")

    object UserList :
        Screen(route = "userlist?follower={followerUsername}&following={followingUsername}") {
        fun passData(
            followerUsername: String = "",
            followingUsername: String = ""
        ): String {
            return "userlist?follower=$followerUsername&following=$followingUsername"
        }
    }

}
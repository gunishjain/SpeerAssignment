package com.gunishjain.speerassignment.ui.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gunishjain.speerassignment.ui.base.UserListScreen
import com.gunishjain.speerassignment.ui.userdetail.UserDetailViewModel

@Composable
fun UserListRoute(
    follower: String? = null,
    following: String? = null,
    viewModel: UserDetailViewModel = hiltViewModel(),
    onUserClick: (username: String) -> Unit
) {

    LaunchedEffect(Unit, block = {
        if (!follower.isNullOrEmpty()) {
            viewModel.getFollowersList(follower)
        } else {
            if (following != null) {
                viewModel.getFollowingList(following)
            }
        }
    })

    val users = viewModel.userListState.collectAsStateWithLifecycle()

    Surface {
        Column(modifier = Modifier.padding(4.dp)) {
            UserListScreen(users.value, onUserClick)
        }
    }


}
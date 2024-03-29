package com.gunishjain.speerassignment.ui.userlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gunishjain.speerassignment.ui.base.UserListScreen
import com.gunishjain.speerassignment.ui.userdetail.UserDetailViewModel

@OptIn(ExperimentalMaterialApi::class)
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
    val refreshing by viewModel.isLoading.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(refreshing, {
            if (follower != null) {
                viewModel.getFollowersList(follower)
            } else {
                if (following != null) {
                    viewModel.getFollowingList(following)
                }
            }
        })



    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        Column(modifier = Modifier.padding(4.dp)) {
            UserListScreen(users.value, onUserClick)
        }
        PullRefreshIndicator(
            refreshing = refreshing, state = pullRefreshState, modifier = Modifier.align(
                Alignment.TopCenter
            )
        )
    }


}
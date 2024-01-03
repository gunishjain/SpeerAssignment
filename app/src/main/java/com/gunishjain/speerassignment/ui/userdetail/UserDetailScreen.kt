package com.gunishjain.speerassignment.ui.userdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gunishjain.speerassignment.data.models.UserDetail
import com.gunishjain.speerassignment.navigation.Screen
import com.gunishjain.speerassignment.ui.base.ShowProgressBar
import com.gunishjain.speerassignment.ui.base.ShowToast
import com.gunishjain.speerassignment.ui.base.UiState

@Composable
fun UserDetailRoute(
    navController: NavController,
    username: String,
    viewModel: UserDetailViewModel = hiltViewModel()

) {

    LaunchedEffect(Unit, block = {
        viewModel.getUserDetail(username)
    })

    val user = viewModel.uiState.collectAsStateWithLifecycle()

    Surface {
        Column(modifier = Modifier.padding(4.dp)) {
            CoinDetailCard(user.value, navController)
        }
    }


}

@Composable
fun CoinDetailCard(
    uiState: UiState<UserDetail>,
    navController: NavController
) {

    when (uiState) {
        is UiState.Success -> {
            UserDetailItem(uiState.data, navController)
        }

        is UiState.Loading -> {
            ShowProgressBar()
        }

        is UiState.Error -> {
            ShowToast(uiState.message)
        }

    }

}

@Composable
fun UserDetailItem(
    user: UserDetail,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = user.avatar_url,
            contentDescription = user.username,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(CircleShape)
        )
        NameText(user.name)
        DetailText(user.username)
        DetailText(user.bio)
        FollowerTextClickable(user, navController)
        FollowingTextClickable(user, navController)
    }
}

@Composable
fun NameText(name: String?) {
    if (!name.isNullOrEmpty()) {
        Text(
            text = name,
            fontSize = 50.sp,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}


@Composable
fun DetailText(detail: String?) {
    if (!detail.isNullOrEmpty()) {
        Text(
            text = detail,
            fontSize = 18.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun FollowerTextClickable(user: UserDetail, navController: NavController) {
    Text(
        text = "${user.followers} Followers",
        fontSize = 18.sp,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Black,
        maxLines = 2,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                navController.navigate(route = Screen.UserList.passData(followerUsername = user.username))
            }
    )
}

@Composable
fun FollowingTextClickable(user: UserDetail, navController: NavController) {
    Text(
        text = "${user.following} Following",
        fontSize = 18.sp,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Black,
        maxLines = 2,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                navController.navigate(route = Screen.UserList.passData(followingUsername = user.username))
            }
    )
}

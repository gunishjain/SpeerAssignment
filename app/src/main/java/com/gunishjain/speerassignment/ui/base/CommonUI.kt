package com.gunishjain.speerassignment.ui.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gunishjain.speerassignment.data.models.User

@Composable
fun ShowProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun ShowToast(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp)
        )
    }
}


@Composable
fun UserListScreen(usersState: UiState<List<User>>, onUserClick: (username: String) -> Unit) {

    when (usersState) {
        is UiState.Success -> {
            if (usersState.data.isEmpty()) {
                ShowToast("No User Found")
            } else {
                UserList(usersState.data, onUserClick)
            }
        }

        is UiState.Loading -> {
            ShowProgressBar()
        }

        is UiState.Error -> {
            ShowToast(usersState.message)
        }

    }

}


@Composable
fun UserList(users: List<User>, onUserClick: (username: String) -> Unit) {
    LazyColumn {
        items(users.size) { index ->
            UserCard(users[index], onUserClick)
        }
    }

}


@Composable
fun UserCard(user: User, onUserClick: (username: String) -> Unit) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBFABCB),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    if (user.username.isNotEmpty()) {
                        onUserClick(user.username)
                    }
                },
            verticalAlignment = Alignment.CenterVertically
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

            Text(
                text = user.username,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontSize = 22.sp,
                maxLines = 2,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

}



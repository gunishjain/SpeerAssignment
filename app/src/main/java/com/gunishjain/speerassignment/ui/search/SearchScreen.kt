package com.gunishjain.speerassignment.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gunishjain.speerassignment.ui.base.UserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenRoute(
    onUserClick: (username: String) -> Unit,
    viewModel: SearchProfileViewModel = hiltViewModel(),
) {

    val searchResult = viewModel.uiState.collectAsStateWithLifecycle()
    val uiState = searchResult.value
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(4.dp)) {

        SearchBar(
            query = text,
            onQueryChange = {
                text = it
                viewModel.onQuerySearch(it)
            },
            onSearch = {
                active = false
            },
            active = active,
            placeholder = {
                Text(text = "Search Users")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            content = {
                UserListScreen(uiState, onUserClick)
            },
            onActiveChange = {
                active = it
            },
            tonalElevation = 0.dp
        )
    }

}
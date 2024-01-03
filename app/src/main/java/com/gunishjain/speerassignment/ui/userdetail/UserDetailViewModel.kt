package com.gunishjain.speerassignment.ui.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunishjain.speerassignment.data.models.User
import com.gunishjain.speerassignment.data.models.UserDetail
import com.gunishjain.speerassignment.data.repository.GitHubRepository
import com.gunishjain.speerassignment.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: GitHubRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UserDetail>>(UiState.Loading)
    val uiState: StateFlow<UiState<UserDetail>> = _uiState

    private val _userListState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val userListState: StateFlow<UiState<List<User>>> = _userListState

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getUserDetail(username)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


    fun getFollowersList(username: String) {
        viewModelScope.launch {
            _userListState.value = UiState.Loading
            repository.getFollowersList(username)
                .catch { e ->
                    _userListState.value = UiState.Error(e.toString())
                }
                .collect {
                    _userListState.value = UiState.Success(it)
                }
        }
    }

    fun getFollowingList(username: String) {
        viewModelScope.launch {
            _userListState.value = UiState.Loading
            repository.getFollowingList(username)
                .catch { e ->
                    _userListState.value = UiState.Error(e.toString())
                }
                .collect {
                    _userListState.value = UiState.Success(it)
                }
        }
    }

}
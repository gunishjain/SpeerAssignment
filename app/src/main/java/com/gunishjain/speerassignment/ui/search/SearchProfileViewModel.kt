package com.gunishjain.speerassignment.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunishjain.speerassignment.data.models.User
import com.gunishjain.speerassignment.data.repository.GitHubRepository
import com.gunishjain.speerassignment.ui.base.UiState
import com.gunishjain.speerassignment.utils.AppConstant.DEBOUNCE_TIMEOUT
import com.gunishjain.speerassignment.utils.AppConstant.MIN_SEARCH_CHAR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchProfileViewModel @Inject constructor(private val repository: GitHubRepository) :
    ViewModel() {

    private val searchText = MutableStateFlow("")
    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<User>>> = _uiState

    init {
        createSearchFlow()
    }

    private fun createSearchFlow() {
        viewModelScope.launch {
            searchText.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if (it.isNotEmpty() && it.length >= MIN_SEARCH_CHAR) {
                        return@filter true
                    } else {
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }.distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest repository.getSearchResult(it)
                        .catch { e ->
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun onQuerySearch(query: String) {
        searchText.value = query
    }

}
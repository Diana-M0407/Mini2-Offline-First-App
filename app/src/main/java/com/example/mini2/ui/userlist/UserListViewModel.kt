package com.example.mini2.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mini2.data.UserRepository
import com.example.mini2.data.local.UserEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UserListUiState(
    val users: List<UserEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val usersFlow = searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) repository.getAllUsers()
            else repository.searchUsers(query)
        }

    private val _uiState = MutableStateFlow(UserListUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            usersFlow.collect { list ->
                _uiState.update { it.copy(users = list, isLoading = false) }
            }
        }

        viewModelScope.launch {
            repository.refreshUsers()
        }
    }

    fun updateSearch(text: String) {
        searchQuery.value = text
    }
}

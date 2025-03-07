package com.vimal.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vimal.myapplication.model.Post
import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.usecases.GetPostsUseCase
import com.vimal.myapplication.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isPostLoading = MutableStateFlow(true)
    val isPostLoading: StateFlow<Boolean> = _isPostLoading.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _isUserLoading = MutableStateFlow(true)
    val isUserLoading: StateFlow<Boolean> = _isUserLoading.asStateFlow()

    init {
        fetchPosts()
        fetchUsers()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            getPostsUseCase()
                .onEach { posts ->
                    delay(2000)
                    _posts.value = posts
                    _isPostLoading.value = false
                }
                .launchIn(this)
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            getUsersUseCase()
                .onEach { users ->
                    delay(2000)
                    _users.value = users
                    _isUserLoading.value = false
                }
                .launchIn(this)
        }
    }
}
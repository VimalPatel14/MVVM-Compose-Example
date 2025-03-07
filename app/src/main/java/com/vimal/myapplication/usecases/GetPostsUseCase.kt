package com.vimal.myapplication.usecases

import com.vimal.myapplication.model.Post
import com.vimal.myapplication.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(): Flow<List<Post>> {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchPostsFromApiIfNeeded() // Fetch only if DB is empty
        }
        return repository.getPosts()
    }
}
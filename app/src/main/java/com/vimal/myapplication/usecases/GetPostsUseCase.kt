package com.vimal.myapplication.usecases

import com.vimal.myapplication.model.Post
import com.vimal.myapplication.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(): Flow<List<Post>> = repository.getPosts()
}
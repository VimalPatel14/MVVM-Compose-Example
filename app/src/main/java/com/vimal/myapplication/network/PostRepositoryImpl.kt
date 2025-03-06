package com.vimal.myapplication.network

import com.vimal.myapplication.model.Post
import com.vimal.myapplication.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PostRepository {
    override fun getPosts(): Flow<List<Post>> = flow {
        val posts = apiService.getPosts().map { it.toDomain() }
        emit(posts)
    }
}
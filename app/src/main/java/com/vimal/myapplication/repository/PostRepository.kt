package com.vimal.myapplication.repository

import com.vimal.myapplication.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<Post>>
}
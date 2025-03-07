package com.vimal.myapplication.repository

import com.vimal.myapplication.db.dao.PostDao
import com.vimal.myapplication.model.Post
import com.vimal.myapplication.network.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PostRepository@Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {
    fun getPosts(): Flow<List<Post>> = postDao.getAllPosts()

    suspend fun fetchPostsFromApiIfNeeded() {
        val cachedPosts = postDao.getAllPosts().first() // Get data from Room
        if (cachedPosts.isEmpty()) { // Fetch from API only if DB is empty
            val response = apiService.getPosts()
            delay(5000)
            postDao.insertPosts(response.map { Post(it.id, it.title, it.body) })
        }
    }
}
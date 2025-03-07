package com.vimal.myapplication.network

import com.vimal.myapplication.model.Post
import com.vimal.myapplication.model.user.User
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("users")
    suspend fun getUsers(): List<User>
}
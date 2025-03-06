package com.vimal.myapplication.network

import com.vimal.myapplication.model.PostDto
import com.vimal.myapplication.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
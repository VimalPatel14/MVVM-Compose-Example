 package com.vimal.myapplication.model

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): Post {
        return Post(title, body)
    }
}
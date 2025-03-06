package com.vimal.myapplication.network

import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override fun getUsers(): Flow<List<User>> = flow {
        val posts = apiService.getUsers().map { it.toDomain() }
        emit(posts)
    }
}
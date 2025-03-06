package com.vimal.myapplication.repository

import com.vimal.myapplication.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
}
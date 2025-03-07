package com.vimal.myapplication.repository

import com.vimal.myapplication.db.dao.UserDao
import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.network.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    fun getUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun fetchUsersFromApiIfNeeded() {
        val cachedUsers = userDao.getAllUsers().first() // Get data from Room
        if (cachedUsers.isEmpty()) {
            // Fetch from API only if DB is empty
            val response = apiService.getUsers()
            userDao.insertUsers(response.map { User(it.id, it.address, it.company, it.email, it.name, it.phone, it.username, it.website) })
        }
    }
}
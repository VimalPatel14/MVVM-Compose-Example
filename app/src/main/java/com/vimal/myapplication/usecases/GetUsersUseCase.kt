package com.vimal.myapplication.usecases

import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> = repository.getUsers()
}
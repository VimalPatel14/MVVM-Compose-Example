package com.vimal.myapplication.usecases

import com.vimal.myapplication.model.user.User
import com.vimal.myapplication.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchUsersFromApiIfNeeded() // Fetch only if DB is empty
        }
        return repository.getUsers()
    }
}
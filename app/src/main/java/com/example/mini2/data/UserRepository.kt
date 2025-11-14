package com.example.mini2.data

import com.example.mini2.data.local.UserDao
import com.example.mini2.data.local.UserEntity
import com.example.mini2.data.remote.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(
    private val userDao: UserDao,
    private val userApi: UserApi
) {

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<UserEntity>> {
        val pattern = "%$query%"
        return userDao.searchUsers(pattern)
    }

    suspend fun refreshUsers() = withContext(Dispatchers.IO) {
        try {
            val remote = userApi.getUsers()
            val entities = remote.map { it.toEntity() }
            userDao.insertUsers(entities)
        } catch (e: Exception) {
            // Silent fail for offline mode
        }
    }
}

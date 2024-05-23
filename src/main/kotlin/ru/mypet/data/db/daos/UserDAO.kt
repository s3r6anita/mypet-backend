package ru.mypet.data.db.daos

import ru.mypet.models.userParams.CreateUserParams
import ru.mypet.models.User

interface UserDAO {
//    suspend fun createUserByVK(params: CreateUserParams): User?
    suspend fun getAll(): List<User>
    suspend fun userByEmail(email: String): User?
    suspend fun userByVKID(vkid: Long): User?
    suspend fun insert(params: CreateUserParams): User?
    suspend fun update(user: User): Boolean
    suspend fun delete(email: String): Boolean
}

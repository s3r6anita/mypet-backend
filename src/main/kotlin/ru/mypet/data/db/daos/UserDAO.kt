package ru.mypet.data.db.daos

import ru.mypet.data.db.CreateUserParams
import ru.mypet.models.User

interface UserDAO {
    suspend fun getAll(): List<User>
    suspend fun user(email: String): User?
    suspend fun insert(params: CreateUserParams): User?
    suspend fun update(user: User): Boolean
    suspend fun delete(email: String): Boolean
}

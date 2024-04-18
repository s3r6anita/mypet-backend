package ru.mypet.data.db.daos

import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.User

interface UserDAO {
    suspend fun getAll(): List<User>
    suspend fun user(email: String): User?
    suspend fun insert(params: CreateUserParams): User?
    suspend fun delete(user: User): Boolean
    //    suspend fun edit(user: User): Boolean
}

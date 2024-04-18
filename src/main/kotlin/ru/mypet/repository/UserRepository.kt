package ru.mypet.repository

import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.User
import ru.mypet.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>
}
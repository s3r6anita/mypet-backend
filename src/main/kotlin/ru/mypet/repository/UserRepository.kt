package ru.mypet.repository

import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.LoginUserParams
import ru.mypet.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: LoginUserParams): BaseResponse<Any>
//    suspend fun refreshTokenUser(params: TokenPair): BaseResponse<Any>
}
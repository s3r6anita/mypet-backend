package ru.mypet.repository

import ru.mypet.models.CreateUserParams
import ru.mypet.models.LoginUserParams
import ru.mypet.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: LoginUserParams): BaseResponse<Any>
//    suspend fun refreshTokenUser(params: TokenPair): BaseResponse<Any>
}
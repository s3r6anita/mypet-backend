package ru.mypet.repository

import ru.mypet.models.userParams.CreateUserParams
import ru.mypet.models.userParams.LoginUserParams
import ru.mypet.utils.BaseResponse

interface UserRepository {
    suspend fun loginUserByVK(params: CreateUserParams): BaseResponse<Any>
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: LoginUserParams): BaseResponse<Any>
//    suspend fun refreshTokenUser(params: TokenPair): BaseResponse<Any>
}
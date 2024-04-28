package ru.mypet.repository

import io.ktor.http.*
import ru.mypet.cache.InMemoryCache
import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.LoginUserParams
import ru.mypet.data.db.daos.UserDAO
import ru.mypet.data.db.daos.UserDAOImpl
import ru.mypet.models.TokenPair
import ru.mypet.security.JwtConfig
import ru.mypet.security.hash
import ru.mypet.utils.BaseResponse
import ru.mypet.utils.isValidEmail

class UserRepositoryImpl(
    private val userDAO: UserDAO = UserDAOImpl()
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (params.email.isValidEmail()) {
            if (isEmailExist(params.email)) {
                BaseResponse.ErrorResponse(errorStatusCode = HttpStatusCode.Conflict, msg = "User with such email is already exist")
            } else {
                val user = userDAO.insert(params)
                if (user != null) {
                    val token = JwtConfig.instance.generateToken(user.email)
                    InMemoryCache.tokens.add(TokenPair(user.email, token))
                    BaseResponse.SuccessResponse(data = hashMapOf("token" to token))
                } else {
                    BaseResponse.ErrorResponse(msg = "You are already registered")
                }
            }
        } else {
            BaseResponse.ErrorResponse(msg = "Invalid email")
        }
    }

    override suspend fun loginUser(params: LoginUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            val user = userDAO.user(params.email)
            if (user != null) {
                if (hash(params.password) == user.password) {
                    val token = JwtConfig.instance.generateToken(user.email)
                    InMemoryCache.tokens.add(TokenPair(user.email, token))
                    BaseResponse.SuccessResponse(data = hashMapOf("hash" to token))
                } else {
                    BaseResponse.ErrorResponse(msg = "Invalid password")
                }
            } else {
                BaseResponse.ErrorResponse(msg = "Invalid email")
            }
        } else {
            BaseResponse.ErrorResponse(msg = "User with this email does not exist")
        }
    }

//    override suspend fun refreshTokenUser(params: TokenPair): BaseResponse<Any> {
//        val tokenPair = InMemoryCache.tokens.firstOrNull{ it == params }
//        return if (tokenPair != null) {
//            InMemoryCache.tokens.remove(tokenPair)
//            val newToken = JwtConfig.instance.generateToken(params.email)
//            InMemoryCache.tokens.add(TokenPair(params.email, newToken))
//            BaseResponse.SuccessResponse(data = params, hash = hashMapOf("token" to newToken))
//        } else {
//            BaseResponse.ErrorResponse(msg = "Invalid email or token")
//        }
//    }

    private suspend fun isEmailExist(email: String): Boolean = userDAO.user(email) != null
}
package ru.mypet.repository

import ru.mypet.cache.InMemoryCache
import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.daos.UserDAO
import ru.mypet.data.db.daos.UserDAOImpl
import ru.mypet.models.TokenCache
import ru.mypet.security.TokenManager
import ru.mypet.utils.BaseResponse
import ru.mypet.utils.isValidEmail

class UserRepositoryImpl(
    private val tokenManager: TokenManager = TokenManager,
    private val userDAO: UserDAO = UserDAOImpl()
) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {

        return if (params.email.isValidEmail()){
            if (isEmailExist(params.email)) {
                BaseResponse.ErrorResponse(msg = "User with such email is already exist")
            } else {
                val user = userDAO.insert(params)
                if (user != null) {
                    val token = tokenManager.generateToken(user.email)
                    InMemoryCache.token.add(TokenCache(user.email, token))
                    BaseResponse.SuccessResponse(data = user, hash = hashMapOf("token" to token))
                } else {
                    BaseResponse.ErrorResponse()
                }
            }
        } else {
            BaseResponse.ErrorResponse(msg = "Invalid email")
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailExist(email: String): Boolean = userDAO.user(email) != null
}
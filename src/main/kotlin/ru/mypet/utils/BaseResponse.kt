package ru.mypet.utils

import io.ktor.http.*

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val msg: String? = null,
        val hash: HashMap<String, String>? = null
    ) : BaseResponse<T>()

    data class ErrorResponse<T>(
        val exception: T? = null,
        val msg: String? = null
    ) : BaseResponse<T>(/**statusCode = HttpStatusCode.BadRequest*/)
}

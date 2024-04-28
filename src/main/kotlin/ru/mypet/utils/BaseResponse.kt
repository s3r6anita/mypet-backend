package ru.mypet.utils

import io.ktor.http.*

sealed class BaseResponse<T>(
    @Transient // to exclude from serialization
    val statusCode: HttpStatusCode
) {
    data class SuccessResponse<T>(
        val data: T? = null
    ) : BaseResponse<T>(
        statusCode = HttpStatusCode.OK
    )

    data class ErrorResponse<T>(
        @Transient // to exclude from serialization
        private val errorStatusCode: HttpStatusCode = HttpStatusCode.BadRequest,
        val msg: String? = null
    ) : BaseResponse<T>(errorStatusCode)
}

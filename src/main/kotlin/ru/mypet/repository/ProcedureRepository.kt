package ru.mypet.repository

import ru.mypet.data.db.CreateProcedureParams
import ru.mypet.data.db.UpdateProcedureParams
import ru.mypet.utils.BaseResponse

interface ProcedureRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun createProcedure(params: CreateProcedureParams, requester: String): BaseResponse<Any>
    suspend fun updateProcedure(params: UpdateProcedureParams, requester: String): BaseResponse<Any>
    suspend fun deleteProcedure(id: Int, requester: String): BaseResponse<Any>
    suspend fun findById(id: Int, requester: String): BaseResponse<Any>
}

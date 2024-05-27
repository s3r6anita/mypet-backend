package ru.mypet.repository

import ru.mypet.models.ProcedureTitle
import ru.mypet.models.titlesParams.CreateProcedureTitleParams
import ru.mypet.utils.BaseResponse

interface ProcedureTitleRepository {
    suspend fun getAll(): BaseResponse<Any>
    suspend fun updateProcedureTitle(params: ProcedureTitle): BaseResponse<Any>
    suspend fun createProcedureTitle(title: CreateProcedureTitleParams): BaseResponse<Any>
}

package ru.mypet.repository

import ru.mypet.data.db.CreateMedRecordParams
import ru.mypet.data.db.UpdateMedRecordParams
import ru.mypet.utils.BaseResponse

interface MedRecordRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun findById(id: Int, requester: String): BaseResponse<Any>
    suspend fun createMedRecord(params: CreateMedRecordParams, requester: String): BaseResponse<Any>
    suspend fun updateMedRecord(params: UpdateMedRecordParams, requester: String): BaseResponse<Any>
    suspend fun deleteMedRecord(id: Int, requester: String): BaseResponse<Any>
}
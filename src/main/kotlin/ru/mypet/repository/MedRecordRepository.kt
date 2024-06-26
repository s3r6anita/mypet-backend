package ru.mypet.repository

import ru.mypet.models.medrecordParams.CreateMedRecordParams
import ru.mypet.models.medrecordParams.UpdateMedRecordParams
import ru.mypet.utils.BaseResponse

interface MedRecordRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun findById(id: Int, requester: String): BaseResponse<Any>
    suspend fun findByPet(id: Int, requester: String): BaseResponse<Any>
    suspend fun createMedRecord(params: CreateMedRecordParams, requester: String): BaseResponse<Any>
    suspend fun updateMedRecord(params: UpdateMedRecordParams, requester: String): BaseResponse<Any>
    suspend fun deleteMedRecord(id: Int, requester: String): BaseResponse<Any>
}
package ru.mypet.repository

import ru.mypet.data.db.CreateMedRecordParams
import ru.mypet.data.db.UpdateMedRecordParams
import ru.mypet.data.db.daos.MedRecordDAO
import ru.mypet.data.db.daos.MedRecordDAOImpl
import ru.mypet.data.db.daos.PetDAO
import ru.mypet.data.db.daos.PetDAOImpl
import ru.mypet.utils.BaseResponse

class MedRecordRepositoryImpl(
    private val medRecordDAO: MedRecordDAO = MedRecordDAOImpl(),
    private val petDAO: PetDAO = PetDAOImpl()
) : MedRecordRepository {
    override suspend fun findAllByOwner(email: String): BaseResponse<Any> {
        val medRecords = medRecordDAO.getAllByOwner(email)
        return BaseResponse.SuccessResponse(data = medRecords)
    }

    override suspend fun findById(id: Int, requester: String): BaseResponse<Any> {
        val medRecord = medRecordDAO.getById(id)
        return when {
            medRecord == null -> BaseResponse.ErrorResponse(msg = "No such medrecord")
            else -> BaseResponse.SuccessResponse(data = medRecord)
        }
    }

    override suspend fun createMedRecord(params: CreateMedRecordParams, requester: String): BaseResponse<Any> {
        val petById = petDAO.getById(params.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "Deny access")
        }
        return if (medRecordDAO.insert(params) != null) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse()
        }
    }

    override suspend fun updateMedRecord(params: UpdateMedRecordParams, requester: String): BaseResponse<Any> {
        val medRecordById = medRecordDAO.getById(params.id)
            ?: return BaseResponse.ErrorResponse(msg = "No such medrecord")
        if (medRecordById.pet != params.pet) {
            return BaseResponse.ErrorResponse(msg = "Incorrect pet id")
        }
        val petById = petDAO.getById(params.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "Deny access. This is not your pet")
        }
        return if (medRecordDAO.update(params)) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(msg = "Something went wrong")
        }
    }

    override suspend fun deleteMedRecord(id: Int, requester: String): BaseResponse<Any> {
        val medRecordById = medRecordDAO.getById(id) ?: return BaseResponse.ErrorResponse(msg = "No such medrecord")
        val petById = petDAO.getById(medRecordById.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "This is not your pet")
        }
        return if (medRecordDAO.delete(id)) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(msg = "Something went wrong")
        }
    }
}
package ru.mypet.repository

import ru.mypet.data.db.daos.PetDAO
import ru.mypet.data.db.daos.PetDAOImpl
import ru.mypet.data.db.daos.ProcedureDAO
import ru.mypet.data.db.daos.ProcedureDAOImpl
import ru.mypet.models.procedureParams.CreateProcedureParams
import ru.mypet.models.procedureParams.UpdateProcedureParams
import ru.mypet.utils.BaseResponse

class ProcedureRepositoryImpl(
    private val procedureDAO: ProcedureDAO = ProcedureDAOImpl(),
    private val petDAO: PetDAO = PetDAOImpl()
) : ProcedureRepository {

    override suspend fun findAllByOwner(email: String): BaseResponse<Any> {
        val procedures = procedureDAO.getAllByOwner(email)
        return BaseResponse.SuccessResponse(data = procedures)
    }

    override suspend fun findByPet(id: Int, requester: String): BaseResponse<Any> {
        val procedures = procedureDAO.getByPetId(id)
        val petById = petDAO.getById(id) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "This is not your pet")
        }
        return BaseResponse.SuccessResponse(data = procedures)
    }

    override suspend fun findById(id: Int, requester: String): BaseResponse<Any> {
        val procedure = procedureDAO.getById(id) ?: return BaseResponse.ErrorResponse(msg = "No such procedure")
        val petById = petDAO.getById(procedure.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "This is not your pet")
        }
        return BaseResponse.SuccessResponse(data = procedure)
    }

    override suspend fun createProcedure(params: CreateProcedureParams, requester: String): BaseResponse<Any> {
        val petById = petDAO.getById(params.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "Deny access")
        }
        return if (procedureDAO.insert(params) != null) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse()
        }
    }

    override suspend fun updateProcedure(params: UpdateProcedureParams, requester: String): BaseResponse<Any> {
        val procedureById = procedureDAO.getById(params.id)
            ?: return BaseResponse.ErrorResponse(msg = "No such procedure")
        if (procedureById.pet != params.pet) {
            return BaseResponse.ErrorResponse(msg = "Incorrect pet id")
        }
        val petById = petDAO.getById(params.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "Deny access. This is not your pet")
        }
        return if (procedureDAO.update(params)) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(msg = "Something went wrong")
        }
    }

    override suspend fun deleteProcedure(id: Int, requester: String): BaseResponse<Any> {
        val procedureById = procedureDAO.getById(id) ?: return BaseResponse.ErrorResponse(msg = "No such procedure")
        val petById = petDAO.getById(procedureById.pet) ?: return BaseResponse.ErrorResponse(msg = "No such pet")
        if (petById.owner != requester) {
            return BaseResponse.ErrorResponse(msg = "This is not your pet")
        }
        return if (procedureDAO.delete(id)) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse(msg = "Something went wrong")
        }
    }
}

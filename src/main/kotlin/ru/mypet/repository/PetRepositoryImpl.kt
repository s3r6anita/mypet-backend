package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.UpdatePetParams
import ru.mypet.data.db.daos.PetDAO
import ru.mypet.data.db.daos.PetDAOImpl
import ru.mypet.utils.BaseResponse

class PetRepositoryImpl(
    private val petDAO: PetDAO = PetDAOImpl()
) : PetRepository {
    override suspend fun findAllByOwner(email: String): BaseResponse<Any> {
        val pets = petDAO.getAllByOwner(email)
        return BaseResponse.SuccessResponse(data = pets)
    }

    override suspend fun findById(id: Int, requester: String): BaseResponse<Any> {
        val pet = petDAO.getById(id)
        return if (pet != null)
            if (pet.owner == requester)
                BaseResponse.SuccessResponse(data = pet)
            else
                BaseResponse.ErrorResponse(msg = "This is not your pet")
        else
            BaseResponse.ErrorResponse(msg = "No such pet")
    }

    override suspend fun createPet(params: CreatePetParams, requester: String): BaseResponse<Any> {
        val paramsWithRequester = params.setOwner(requester)
        val pet = petDAO.insert(paramsWithRequester)
        return if (pet != null) {
            BaseResponse.SuccessResponse()
        } else {
            BaseResponse.ErrorResponse()
        }
    }

    override suspend fun updatePet(params: UpdatePetParams, requester: String): BaseResponse<Any> {
        val petById = petDAO.getById(params.id)
        return if (petById != null) {
            if (petById.owner == requester) {
                val update = petDAO.update(params)
                if (update)
                    BaseResponse.SuccessResponse()
                else
                    BaseResponse.ErrorResponse(msg = "Something went wrong")
            } else {
                BaseResponse.ErrorResponse(msg = "This is not your pet")
            }
        } else {
            BaseResponse.ErrorResponse(msg = "No such pet")
        }
    }

    override suspend fun deletePet(id: Int, requester: String): BaseResponse<Any> {
        val pet = petDAO.getById(id)
        return if (pet != null) {
            if (pet.owner == requester) {
                val delete = petDAO.delete(id)
                if (delete)
                    BaseResponse.SuccessResponse()
                else
                    BaseResponse.ErrorResponse(msg = "Something went wrong")
            } else {
                BaseResponse.ErrorResponse(msg = "This is not your pet")
            }
        } else {
            BaseResponse.ErrorResponse(msg = "No such pet")
        }
    }
}
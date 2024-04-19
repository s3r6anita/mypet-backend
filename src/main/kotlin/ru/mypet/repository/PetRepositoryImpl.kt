package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.daos.PetDAO
import ru.mypet.data.db.daos.PetDAOImpl
import ru.mypet.models.Pet
import ru.mypet.utils.BaseResponse

class PetRepositoryImpl(
    private val petDAO: PetDAO = PetDAOImpl()
) : PetRepository {
    override suspend fun findAllByOwner(email: String): BaseResponse<Any> {
        val pets = petDAO.getAllByOwner(email)
        return BaseResponse.SuccessResponse(data = pets)
    }

    override suspend fun findById(id: Int): BaseResponse<Any> {
        val pet = petDAO.getById(id)
        return if (pet != null)
            BaseResponse.SuccessResponse(data = pet)
        else
            BaseResponse.ErrorResponse(msg = "No such pet")
    }

    override suspend fun createPet(params: CreatePetParams): BaseResponse<Any> {
        val pet = petDAO.insert(params)
        return if (pet != null) {
            BaseResponse.SuccessResponse(data = pet)
        } else {
            BaseResponse.ErrorResponse()
        }
    }

    override suspend fun updatePet(pet: Pet): BaseResponse<Any> {
        val petById = petDAO.getById(pet.id)
        return if (petById != null) {
            val update = petDAO.update(petById)
            if (update)
                BaseResponse.SuccessResponse(msg = "Updated successfully")
            else
                BaseResponse.ErrorResponse(msg = "Something went wrong")
        } else {
            BaseResponse.ErrorResponse(msg = "No such pet")
        }
    }

    override suspend fun deletePet(id: Int): BaseResponse<Any> {
        val pet = petDAO.getById(id)
        return if (pet != null) {
            val delete = petDAO.delete(id)
            if (delete)
                BaseResponse.SuccessResponse(msg = "Deleted successfully")
            else
                BaseResponse.ErrorResponse(msg = "Something went wrong")
        } else {
            BaseResponse.ErrorResponse(msg = "No such pet")
        }
    }
}
package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.daos.PetDAO
import ru.mypet.data.db.daos.PetDAOImpl
import ru.mypet.models.Pet
import ru.mypet.utils.BaseResponse

class PetRepositoryImpl(
    private val petDAO: PetDAO = PetDAOImpl()
) : PetRepository {
    override suspend fun findAllByOwner(): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(): BaseResponse<Any> {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override suspend fun deletePet(pet: Pet): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
}
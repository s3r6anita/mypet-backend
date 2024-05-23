package ru.mypet.repository

import ru.mypet.models.petParams.CreatePetParams
import ru.mypet.models.petParams.UpdatePetParams
import ru.mypet.utils.BaseResponse

interface PetRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun findById(id: Int, requester: String): BaseResponse<Any>
    suspend fun createPet(params: CreatePetParams, requester: String): BaseResponse<Any>
    suspend fun updatePet(params: UpdatePetParams, requester: String): BaseResponse<Any>
    suspend fun deletePet(id: Int, requester: String): BaseResponse<Any>
}
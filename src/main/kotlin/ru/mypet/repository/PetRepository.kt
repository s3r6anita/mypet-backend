package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.models.Pet
import ru.mypet.utils.BaseResponse

interface PetRepository {
    suspend fun findAllByOwner(): BaseResponse<Any>
    suspend fun findById(): BaseResponse<Any>
    suspend fun createPet(params: CreatePetParams): BaseResponse<Any>
    suspend fun updatePet(pet: Pet): BaseResponse<Any>
    suspend fun deletePet(pet: Pet): BaseResponse<Any>
}
package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.models.Pet
import ru.mypet.utils.BaseResponse

interface PetRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun findById(id: Int): BaseResponse<Any>
    suspend fun createPet(params: CreatePetParams): BaseResponse<Any>
    suspend fun updatePet(pet: Pet): BaseResponse<Any>
    suspend fun deletePet(id: Int): BaseResponse<Any>
}
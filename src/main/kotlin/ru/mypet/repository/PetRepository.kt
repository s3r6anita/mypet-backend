package ru.mypet.repository

import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.UpdatePetParams
import ru.mypet.utils.BaseResponse

interface PetRepository {
    suspend fun findAllByOwner(email: String): BaseResponse<Any>
    suspend fun findById(id: Int, requester: String): BaseResponse<Any> /** излишен (хотя возможно понадобится для экрана профиля) */
    suspend fun createPet(params: CreatePetParams, requester: String): BaseResponse<Any>
    suspend fun updatePet(params: UpdatePetParams, requester: String): BaseResponse<Any>
    suspend fun deletePet(id: Int, requester: String): BaseResponse<Any>
}
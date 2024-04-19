package ru.mypet.data.db.daos

import ru.mypet.data.db.CreatePetParams
import ru.mypet.models.Pet

interface PetDAO {
    suspend fun getAllByOwner(email: String): List<Pet>
    suspend fun getById(id: Int): Pet?
    suspend fun insert(params: CreatePetParams): Pet?
    suspend fun update(pet: Pet): Boolean
    suspend fun delete(id: Int): Boolean
}
package ru.mypet.data.db.daos

import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.UpdatePetParams
import ru.mypet.models.Pet

interface PetDAO {
    suspend fun getAllByOwner(email: String): List<Pet>
    suspend fun getById(id: Int): Pet?
    suspend fun insert(params: CreatePetParams): Pet?
    suspend fun update(params: UpdatePetParams): Boolean
    suspend fun delete(id: Int): Boolean
}
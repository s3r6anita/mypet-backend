package ru.mypet.data.db.daos

import ru.mypet.models.Procedure
import ru.mypet.models.procedureParams.CreateProcedureParams
import ru.mypet.models.procedureParams.UpdateProcedureParams

interface ProcedureDAO {
    suspend fun getAllByOwner(email: String): List<Procedure>
    suspend fun getByPetId(id: Int): List<Procedure>
    suspend fun getById(id: Int): Procedure?
    suspend fun insert(params: CreateProcedureParams): Procedure?
    suspend fun update(params: UpdateProcedureParams): Boolean
    suspend fun delete(id: Int): Boolean
}
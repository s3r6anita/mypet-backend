package ru.mypet.data.db.daos

import ru.mypet.data.db.CreateProcedureParams
import ru.mypet.data.db.UpdateProcedureParams
import ru.mypet.models.Procedure

interface ProcedureDAO {
    suspend fun getAllByOwner(email: String): List<Procedure>
    suspend fun getById(id: Int): Procedure?
    suspend fun insert(params: CreateProcedureParams): Procedure?
    suspend fun update(params: UpdateProcedureParams): Boolean
    suspend fun delete(id: Int): Boolean
}
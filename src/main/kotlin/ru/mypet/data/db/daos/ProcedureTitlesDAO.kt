package ru.mypet.data.db.daos

import ru.mypet.models.ProcedureTitle
import ru.mypet.models.titlesParams.CreateProcedureTitleParams

interface ProcedureTitlesDAO {
    suspend fun getAll(): List<ProcedureTitle>
    suspend fun getById(id: Int): ProcedureTitle?
    suspend fun insert(params: CreateProcedureTitleParams): Int?
    suspend fun update(title: ProcedureTitle): Boolean
}

package ru.mypet.data.db.daos

import ru.mypet.data.db.CreateMedRecordParams
import ru.mypet.data.db.UpdateMedRecordParams
import ru.mypet.models.MedRecord

interface MedRecordDAO {
    suspend fun getAllByOwner(email: String): List<MedRecord>
    suspend fun getById(id: Int): MedRecord?
    suspend fun insert(params: CreateMedRecordParams): MedRecord?
    suspend fun update(params: UpdateMedRecordParams): Boolean
    suspend fun delete(id: Int): Boolean
}
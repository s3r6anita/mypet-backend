package ru.mypet.data.db.daos

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.mypet.models.medrecordParams.CreateMedRecordParams
import ru.mypet.data.db.DatabaseFactory.dbQuery
import ru.mypet.data.db.tables.MedRecords
import ru.mypet.data.db.tables.Pets
import ru.mypet.models.medrecordParams.UpdateMedRecordParams
import ru.mypet.models.MedRecord
import ru.mypet.utils.PetDateTimeFormatter
import java.time.LocalDateTime

class MedRecordDAOImpl : MedRecordDAO {

    private fun resultRowToMedRecord(row: ResultRow) = MedRecord(
        title = row[MedRecords.title],
        date = LocalDateTime.parse(row[MedRecords.date], PetDateTimeFormatter.dateTime),
        notes = row[MedRecords.notes],
        pet = row[MedRecords.pet],
        id = row[MedRecords.id]
    )

    override suspend fun getAllByOwner(email: String): List<MedRecord> = dbQuery {
        MedRecords.join(Pets, JoinType.LEFT, MedRecords.pet, Pets.id)
            .select { Pets.owner eq email }
            .map(::resultRowToMedRecord)
    }


    override suspend fun getById(id: Int): MedRecord? = dbQuery {
        MedRecords.select { MedRecords.id eq id }
            .map(::resultRowToMedRecord)
            .singleOrNull()
    }

    override suspend fun insert(params: CreateMedRecordParams): MedRecord? = dbQuery {
        val insertStatement = MedRecords.insert {
            it[title] = params.title
            it[date] = params.date
            it[notes] = params.notes
            it[pet] = params.pet
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToMedRecord)
    }

    override suspend fun update(params: UpdateMedRecordParams): Boolean = dbQuery {
        MedRecords.update({ MedRecords.id eq params.id }) {
            it[title] = params.title
            it[date] = params.date.format(PetDateTimeFormatter.dateTime)
            it[notes] = params.notes
            it[pet] = params.pet
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        MedRecords.deleteWhere { MedRecords.id eq id } > 0
    }
}

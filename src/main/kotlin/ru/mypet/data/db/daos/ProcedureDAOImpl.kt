package ru.mypet.data.db.daos

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.mypet.data.db.CreateProcedureParams
import ru.mypet.data.db.DatabaseFactory.dbQuery
import ru.mypet.data.db.Pets
import ru.mypet.data.db.Procedures
import ru.mypet.data.db.UpdateProcedureParams
import ru.mypet.models.Procedure
import ru.mypet.utils.PetDateTimeFormatter
import java.time.LocalDateTime

class ProcedureDAOImpl : ProcedureDAO {
    private fun resultRowToProcedure(row: ResultRow) = Procedure(
        title = row[Procedures.title],
        isDone = row[Procedures.isDone],
        frequency = row[Procedures.frequency],
        dateDone = LocalDateTime.parse(row[Procedures.dateDone], PetDateTimeFormatter.dateTime),
        notes = row[Procedures.notes],
        reminder = row[Procedures.reminder]?.let { LocalDateTime.parse(it, PetDateTimeFormatter.dateTime) },
        pet  = row[Procedures.pet],
        inMedCard = row[Procedures.inMedCard],
        id = row[Procedures.id],
    )

    override suspend fun getAllByOwner(email: String): List<Procedure> {
        val procedures = dbQuery {
            Procedures.join(Pets, JoinType.INNER, Procedures.pet, Pets.id)
                .select { Pets.owner eq email }
                .map(::resultRowToProcedure)
        }
        return procedures
    }

    override suspend fun insert(params: CreateProcedureParams): Procedure? = dbQuery {
        val insertStatement = Procedures.insert {
            it[title] = params.title
            it[isDone] = params.isDone
            it[frequency] = params.frequency
            it[dateDone] = params.dateDone
            it[notes] = params.notes
            it[reminder] = params.reminder
            it[pet] = params.pet
            it[inMedCard] = params.inMedCard
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProcedure)
    }

    override suspend fun getById(id: Int): Procedure? {
        val procedure = dbQuery {
            Procedures.select {  Procedures.id eq id }.map(::resultRowToProcedure).singleOrNull()
        }
        return procedure
    }

    override suspend fun update(params: UpdateProcedureParams): Boolean = dbQuery {
        Procedures.update({ Procedures.id eq params.id }) {
            it[title] = params.title
            it[isDone] = params.isDone
            it[frequency] = params.frequency
            it[dateDone] = params.dateDone.format(PetDateTimeFormatter.dateTime)
            it[notes] = params.notes
            it[reminder] = params.reminder?.format(PetDateTimeFormatter.dateTime)
            it[inMedCard] = params.inMedCard
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Procedures.deleteWhere { Procedures.id eq id } > 0
    }
}
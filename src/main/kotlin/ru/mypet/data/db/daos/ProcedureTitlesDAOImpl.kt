package ru.mypet.data.db.daos

import org.jetbrains.exposed.sql.*
import ru.mypet.data.db.DatabaseFactory.dbQuery
import ru.mypet.data.db.tables.ProcedureTitles
import ru.mypet.models.ProcedureTitle
import ru.mypet.models.titlesParams.CreateProcedureTitleParams

class ProcedureTitlesDAOImpl : ProcedureTitlesDAO {
    private fun resultRowToProcedureTitle(row: ResultRow) = ProcedureTitle(
        id = row[ProcedureTitles.id],
        name = row[ProcedureTitles.name],
        type = row[ProcedureTitles.type]
    )

    override suspend fun getAll(): List<ProcedureTitle> = dbQuery {
        ProcedureTitles.selectAll().map(::resultRowToProcedureTitle)
    }

    override suspend fun getById(id: Int): ProcedureTitle? {
        val title = dbQuery {
            ProcedureTitles.select { ProcedureTitles.id eq id }.map(::resultRowToProcedureTitle).singleOrNull()
        }
        return title
    }

    override suspend fun insert(params: CreateProcedureTitleParams): Int? = dbQuery {
        val insertStatement = ProcedureTitles.insert {
            it[name] = params.name
            it[type] = params.type
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProcedureTitle)?.id
    }

    override suspend fun update(title: ProcedureTitle): Boolean = dbQuery {
        ProcedureTitles.update({ ProcedureTitles.id eq title.id }) {
            it[name] = title.name
            it[type] = title.type
        } > 0
    }

}
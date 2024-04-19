package ru.mypet.data.db.daos

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.mypet.data.db.CreatePetParams
import ru.mypet.data.db.DatabaseFactory.dbQuery
import ru.mypet.data.db.Pets
import ru.mypet.models.Pet
import ru.mypet.utils.PetDateTimeFormatter
import java.time.LocalDate

class PetDAOImpl : PetDAO {
    private fun resultRowToPet(row: ResultRow) = Pet(
        id = row[Pets.id],
        name = row[Pets.name],
        kind = row[Pets.kind],
        breed = row[Pets.breed],
        sex = row[Pets.sex],
        birthday = LocalDate.parse(row[Pets.birthday], PetDateTimeFormatter.date),
        color = row[Pets.color],
        coat = row[Pets.coat],
        microchipNumber = row[Pets.microchipNumber],
        owner = row[Pets.owner]
    )

    override suspend fun getAllByOwner(email: String): List<Pet> {
        val pet = dbQuery {
            Pets.select { Pets.owner eq email}.map(::resultRowToPet)
        }
        return pet
    }

    override suspend fun getById(id: Int): Pet? {
        val pet = dbQuery {
            Pets.select { Pets.id eq id }.map(::resultRowToPet).singleOrNull()
        }
        return pet
    }

    override suspend fun insert(params: CreatePetParams): Pet? = dbQuery {
        val insertStatement = Pets.insert {
            it[name] = params.name
            it[kind] = params.kind
            it[breed] = params.breed
            it[sex] = params.sex
            it[birthday] = params.birthday // can be error there
            it[color] = params.color
            it[coat] = params.coat
            it[microchipNumber] = params.microchipNumber
            it[owner] = params.owner!!
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPet)
    }

    override suspend fun update(pet: Pet): Boolean = dbQuery {
        Pets.update({ Pets.id eq pet.id }) {
            it[name] = pet.name
            it[kind] = pet.kind
            it[breed] = pet.breed
            it[sex] = pet.sex
            it[birthday] = pet.birthday.format(PetDateTimeFormatter.date) // can be error there
            it[color] = pet.color
            it[coat] = pet.coat
            it[microchipNumber] = pet.microchipNumber
        } > 0
    }

    override suspend fun delete(id: Int): Boolean = dbQuery {
        Pets.deleteWhere { Pets.id eq id } > 0
    }
}
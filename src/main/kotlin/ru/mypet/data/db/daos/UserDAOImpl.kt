package ru.mypet.data.db.daos

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.mypet.data.db.CreateUserParams
import ru.mypet.data.db.DatabaseFactory.dbQuery
import ru.mypet.data.db.Users
import ru.mypet.models.User
import ru.mypet.security.hash

class UserDAOImpl : UserDAO {
    private fun resultRowToUser(row: ResultRow) = User(
        email = row[Users.email],
        password = row[Users.password],
        name = row[Users.name]
    )

    override suspend fun getAll(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun user(email: String): User? {
        val user = dbQuery {
            Users.select { Users.email eq email }.map(::resultRowToUser).singleOrNull()
        }
        return user
    }

    override suspend fun insert(params: CreateUserParams): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.email] = params.email
            it[Users.password] = hash(params.password)
            it[Users.name] = params.name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun update(user: User): Boolean = dbQuery {
        Users.update({ Users.email eq user.email }) {
            it[Users.email] = user.email
            it[Users.password] = user.password
            it[Users.name] = user.name
        } > 0
    }

    override suspend fun delete(email: String): Boolean = dbQuery {
        Users.deleteWhere { Users.email eq email } > 0
    }
}

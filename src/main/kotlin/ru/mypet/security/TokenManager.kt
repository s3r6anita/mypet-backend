package ru.mypet.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import java.util.*

object TokenManager {
    private val config = HoconApplicationConfig(ConfigFactory.load())

    private val audience = config.property("ktor.security.jwt.audience").getString()
    private val secret = config.property("ktor.security.jwt.secret").getString()
    private val issuer = config.property("ktor.security.jwt.issuer").getString()
    private val claim = config.property("ktor.security.jwt.claim").getString()
    private val expirationPeriod = config.property("ktor.security.jwt.expiration_time").getString().toLong()
    private val algorithm = Algorithm.HMAC512(secret)

    fun generateToken(email: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(claim, email)
            .withExpiresAt(getExpirationTime())
            .sign(algorithm)
    }

    private fun getExpirationTime() = Date(System.currentTimeMillis() + expirationPeriod)
}
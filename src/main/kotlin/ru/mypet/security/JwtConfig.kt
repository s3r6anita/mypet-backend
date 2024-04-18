package ru.mypet.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import java.util.*


class JwtConfig private constructor() {

    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier =
        JWT
            .require(algorithm)
            .withAudience(audience)
            .withIssuer(issuer)
            .build()

    fun generateToken(email: String): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(claim, email)
            .withExpiresAt(getExpirationTime())
            .sign(algorithm)


    private fun getExpirationTime() = Date(System.currentTimeMillis() + expirationPeriod)

    companion object {
        private val config = HoconApplicationConfig(ConfigFactory.load())
        val secret = config.property("ktor.security.jwt.secret").getString()
        val issuer = config.property("ktor.security.jwt.issuer").getString()
        val audience = config.property("ktor.security.jwt.audience").getString()
        val claim = config.property("ktor.security.jwt.claim").getString()
        val expirationPeriod = config.property("ktor.security.jwt.expiration_time").getString().toLong()

        lateinit var instance: JwtConfig
            private set

        fun initialize() {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = JwtConfig()
                }
            }
        }
    }
}




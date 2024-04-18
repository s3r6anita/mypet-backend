package ru.mypet.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val SECRET_KEY = "1029384756"
private val ALGORITHM = "HmacSHA1"

private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password: String): String {
    val hMac = Mac.getInstance(ALGORITHM)
    hMac.init(HMAC_KEY)
    return hex(hMac.doFinal(password.toByteArray(Charsets.UTF_8)))
}
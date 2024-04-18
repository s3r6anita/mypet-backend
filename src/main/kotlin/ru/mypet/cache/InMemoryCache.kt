package ru.mypet.cache

import ru.mypet.models.TokenCache

object InMemoryCache {
    val token: MutableList<TokenCache> = mutableListOf()
}
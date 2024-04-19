package ru.mypet.cache

import ru.mypet.models.TokenPair

object InMemoryCache {
    val tokens: MutableList<TokenPair> = mutableListOf()
}
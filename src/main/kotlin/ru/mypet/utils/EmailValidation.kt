package ru.mypet.utils

fun String.isValidEmail(): Boolean = this.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())
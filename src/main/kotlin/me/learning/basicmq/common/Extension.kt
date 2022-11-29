package me.learning.basicmq.common

import me.learning.basicmq.controller.exception.MissingFieldRequiredException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Extension {
    fun LocalDate?.systemFormat () : String? {
        return this?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    }

    fun LocalDateTime?.systemFormat () : String? {
        return this?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"))
    }
}

fun <T> getOrElseThrow(name: String, field: T?): T {
    return field ?: throw MissingFieldRequiredException("The param[$name]")
}
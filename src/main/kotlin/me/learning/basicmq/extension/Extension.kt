package me.learning.basicmq.extension

import me.learning.basicmq.controller.exception.MissingFieldRequiredException

object Extension {
}

fun <T> getOrElseThrow(name: String, field: T?): T {
    return field ?: throw MissingFieldRequiredException("The param[$name]")
}
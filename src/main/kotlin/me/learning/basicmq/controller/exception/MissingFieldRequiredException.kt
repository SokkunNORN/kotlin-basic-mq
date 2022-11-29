package me.learning.basicmq.controller.exception

data class MissingFieldRequiredException(val field: String) : RuntimeException(field)
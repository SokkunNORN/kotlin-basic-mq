package me.learning.basicmq.controller.exception

import java.lang.RuntimeException

data class TooManyRequestsException(val description: String) : RuntimeException(description)

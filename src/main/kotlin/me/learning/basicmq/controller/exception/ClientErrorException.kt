package me.learning.basicmq.controller.exception

import me.learning.basicmq.controller.exception.handler.ErrorCode

data class ClientErrorException(val errorCode: ErrorCode, val description: String?) : RuntimeException(description)
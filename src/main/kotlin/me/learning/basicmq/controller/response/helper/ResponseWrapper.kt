package me.learning.basicmq.controller.response.helper

import me.learning.basicmq.controller.exception.ClientErrorException
import me.learning.basicmq.controller.exception.handler.ErrorCode

class ResponseWrapper<T>(val status: Status, val data: T?) {

    companion object {
        fun <T> data(data: T) : ResponseWrapper<T> = ResponseWrapper(Status.SUCCESSFUL, data)

        fun error(error: ErrorCode) : ResponseWrapper<Any> {
            val status = Status(error.code, error.message)
            return status.error()
        }

        fun error(error: ErrorCode, message: String) : ResponseWrapper<Any> {
            val status = Status(error.code, String.format(error.message, message).trim())
            return status.error()
        }
    }

    class Status(val errorCode: Number, val errorMessage: String) {
        fun error(): ResponseWrapper<Any> {
            return ResponseWrapper(this, null)
        }

        companion object {
            val SUCCESSFUL = Status(ErrorCode.SUCCESS.code, ErrorCode.SUCCESS.message)
        }
    }
}

fun <T> success(data: T) : ResponseWrapper<T> = ResponseWrapper.data(data)

fun issue(message: String?): Nothing = throw ClientErrorException(errorCode = ErrorCode.CLIENT_ERROR, message)
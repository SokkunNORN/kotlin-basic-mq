package me.learning.basicmq.controller.exception.handler

import me.learning.basicmq.controller.exception.*
import me.learning.basicmq.controller.response.helper.ResponseWrapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerAdvise {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MissingFieldRequiredException::class)
    fun missingFieldRequiredException(
        request: HttpServletRequest?,
        e: MissingFieldRequiredException
    ) : ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = ErrorCode.MISSING_FIELD_REQUIRED, message = e.message ?: "")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(IdNotFoundException::class)
    fun idNotFoundException(
        request: HttpServletRequest?,
        e: IdNotFoundException
    ) : ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = ErrorCode.ID_NOT_FOUND, message = e.message ?: "")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(ClientErrorException::class)
    fun clientErrorException(
        request: HttpServletRequest?,
        e: ClientErrorException
    ) : ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = e.errorCode, message = e.message ?: "")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(RecordExistException::class)
    fun recordAlreadyExistsException(
        request: HttpServletRequest?,
        e: RecordExistException
    ) : ResponseEntity<ResponseWrapper<Any>> {
        log.error("RecordAlreadyException: ", e)
        val response = ResponseWrapper.error(error = ErrorCode.RECORD_ALREADY_EXISTED, message = e.message ?: "")

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingServletRequestParameterException(
        request: HttpServletRequest?,
        e: MissingServletRequestParameterException
    ): ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = ErrorCode.MISSING_FIELD_REQUIRED, message = e.parameterName)

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(
        request: HttpServletRequest?,
        e: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = ErrorCode.METHOD_NOT_ALLOWED, message = "")

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(TooManyRequestsException::class)
    fun tooManyRequestsException(
        request: HttpServletRequest?,
        e: TooManyRequestsException
    ): ResponseEntity<ResponseWrapper<Any>> {
        val response = ResponseWrapper.error(error = ErrorCode.TOO_MANY_REQUESTS, message = e.description)

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response)
    }
}
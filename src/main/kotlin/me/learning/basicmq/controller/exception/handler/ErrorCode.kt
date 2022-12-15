package me.learning.basicmq.controller.exception.handler

enum class ErrorCode(val code: Int, val message: String) {
    SUCCESS(0, ""),
    METHOD_NOT_ALLOWED(2, "Method Not Allowed"),
    ID_NOT_FOUND(3, "%s is not found"),
    MISSING_FIELD_REQUIRED(4, "Missing required field: %s"),
    RECORD_ALREADY_EXISTED(5, "%s record already exists"),
    CLIENT_ERROR(6, "%s"),
    TOO_MANY_REQUESTS(7, "Number of the request reaches the limited threshold")
}
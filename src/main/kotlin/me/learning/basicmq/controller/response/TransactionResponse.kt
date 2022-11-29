package me.learning.basicmq.controller.response

import java.time.LocalDateTime

data class TransactionResponse(
    val currencyCode: String,
    val amount: String,
    val statusCode: String,
    val message: String? = "",
    val createdAt: String? = ""
)

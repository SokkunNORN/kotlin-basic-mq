package me.learning.basicmq.controller.response

data class TransactionResponse(
    val currencyCode: String,
    val amount: String,
    val statusCode: String,
    val message: String? = ""
)

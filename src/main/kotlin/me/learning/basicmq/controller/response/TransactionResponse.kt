package me.learning.basicmq.controller.response

import java.math.BigDecimal

data class TransactionResponse(
    val currencyCode: String,
    val amount: BigDecimal,
    val statusCode: String
)

package me.learning.basicmq.controller.request

import java.math.BigDecimal

data class TransactionRequest(
    val currencyCode: String,
    val amount: BigDecimal
)
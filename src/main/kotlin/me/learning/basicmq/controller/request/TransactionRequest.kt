package me.learning.basicmq.controller.request

import me.learning.basicmq.extension.getOrElseThrow
import java.math.BigDecimal

data class TransactionRequest(
    val currencyCode: String?,
    val amount: BigDecimal?,
    val message: String? = ""
) {
    init {
        getOrElseThrow("currencyCode", this.currencyCode)
        getOrElseThrow("amount", this.amount)
    }
}
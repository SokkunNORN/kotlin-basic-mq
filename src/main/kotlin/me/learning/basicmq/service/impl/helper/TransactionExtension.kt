package me.learning.basicmq.service.impl.helper

import com.fasterxml.jackson.databind.ObjectMapper
import me.learning.basicmq.common.Extension.systemFormat
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.enum.Transfer
import me.learning.basicmq.helper.Extension.systemFormat
import me.learning.basicmq.model.Transaction
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.util.DigestUtils
import java.util.*

fun Transaction.toResponse(): TransactionResponse {
    val currencyCode = Transfer.CurrencyCode.values().find {
        it.name == this.currencyCode
    } ?: Transfer.CurrencyCode.KHR

    return TransactionResponse(
        currencyCode = this.currencyCode,
        amount = this.amount.systemFormat(currencyCode),
        statusCode = this.statusCode,
        message = this.message,
        createdAt = this.createdAt.systemFormat()
    )
}

fun Transaction.getHash(): String? {
    val objectMapper: ObjectMapper = Jackson2ObjectMapperBuilder().createXmlMapper(false).build()
    val json = objectMapper.writeValueAsString(this)
    val dataByte = json.toByteArray()
    val result = DigestUtils.md5Digest(dataByte)
    return Base64.getEncoder().encodeToString(result)
}
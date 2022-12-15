package me.learning.basicmq.model

import me.learning.basicmq.common.Extension.systemFormat
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.enum.Transfer
import me.learning.basicmq.helper.Extension.systemFormat
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "hash")
    var hash: String? = null,

    @Column(name = "currency_code", nullable = false)
    val currencyCode: String = "",

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal = BigDecimal(0),

    @Column(name = "status_code", nullable = false)
    var statusCode: String = "",

    @Column(name = "message")
    var message: String? = "",

    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: LocalDateTime? = LocalDateTime.now()
) {
    fun toResponse () : TransactionResponse {
        val currencyEnum = Transfer.CurrencyCode.values().find { it.name == this.currencyCode } ?: Transfer.CurrencyCode.KHR
        return TransactionResponse(
            currencyCode = this.currencyCode,
            amount = this.amount.systemFormat(currencyEnum),
            statusCode = this.statusCode,
            message = this.message,
            createdAt = this.createdAt.systemFormat()
        )
    }
}
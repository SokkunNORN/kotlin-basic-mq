package me.learning.basicmq.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "currency_code", nullable = false)
    val currencyCode: String = "",

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal = BigDecimal(0),

    @Column(name = "status_code", nullable = false)
    val statusCode: String = ""
)
package me.learning.basicmq.model

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
)
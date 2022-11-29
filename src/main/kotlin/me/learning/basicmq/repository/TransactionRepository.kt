package me.learning.basicmq.repository

import me.learning.basicmq.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TransactionRepository: JpaRepository<Transaction, Long> {
    fun findByHash(hash: String): Optional<Transaction>
}
package me.learning.basicmq.repository

import me.learning.basicmq.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository: JpaRepository<Transaction, Long>
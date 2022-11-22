package me.learning.basicmq.service

import me.learning.basicmq.model.Transaction

interface ITransactionService {
    fun findAll(): List<Transaction>
    fun findById(id: Long): Transaction
    fun saveAllToPending(): Boolean
    fun settlement(): Boolean
}